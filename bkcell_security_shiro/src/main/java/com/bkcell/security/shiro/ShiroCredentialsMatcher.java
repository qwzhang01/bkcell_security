package com.bkcell.security.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class ShiroCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        return doCredentialsMatch((UsernamePasswordToken) token, (SimpleAuthenticationInfo) info);
    }

    private boolean doCredentialsMatch(UsernamePasswordToken token, SimpleAuthenticationInfo info) {
        char[] password = token.getPassword();
        String credentials = String.valueOf(password);
        credentials = ShiroCrypt.encrypt(credentials);

        String passwordInfo = info.getCredentials().toString();
        passwordInfo = ShiroCrypt.encrypt(passwordInfo);
        return credentials.equals(passwordInfo);
    }
}