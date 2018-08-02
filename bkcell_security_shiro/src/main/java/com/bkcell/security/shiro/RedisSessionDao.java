package com.bkcell.security.shiro;

import com.bkcell.security.common.kit.ServletKit;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    private Cache cache() {
        Cache<Object, Object> cache = getCacheManager().getCache(this.getClass().getName());
        return cache;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        cache().put(sessionId.toString(), session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        HttpServletRequest request = ServletKit.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            if (ServletKit.isStaticFile(uri)) {
                return null;
            }
            session = (Session) request.getAttribute("session_" + sessionId);
        }
        if (session == null) {
            session = super.doReadSession(sessionId);
        }
        if (session == null) {
            session = (Session) cache().get(sessionId.toString());
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        HttpServletRequest request = ServletKit.getRequest();
        if (request != null) {
            String uri = request.getServletPath();
            if (ServletKit.isStaticFile(uri)) {
                return;
            }
        }
        super.doUpdate(session);
        cache().put(session.getId().toString(), session);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        cache().remove(session.getId().toString());
    }
}