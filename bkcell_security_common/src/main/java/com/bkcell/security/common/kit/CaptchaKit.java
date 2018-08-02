package com.bkcell.security.common.kit;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptchaKit {
    public static final String CAPTCHA_KEY = "_CAPTCHA_CODE_";

    public static void renderCapcha(HttpServletRequest request, HttpServletResponse response, int imgRandNumber) {
        if (imgRandNumber < 4) {
            imgRandNumber = 4;
        }
        int imgWidth = 16 * imgRandNumber + 12;
        int imgHeight = 26;

        String randonCode = generateRandonCode(imgRandNumber);
        String md5RandonCode = SecureUtil.md5(randonCode);
        request.getSession().setAttribute(CAPTCHA_KEY, md5RandonCode);
        BufferedImage image = new BufferedImage(imgWidth, imgHeight,
                BufferedImage.TYPE_INT_RGB);
        drawGraphic(image, imgRandNumber, imgWidth, imgHeight, randonCode);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(image, "jpeg", sos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (sos != null) {
                try {
                    sos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean validate(String inputRandomCode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionAttribute = (String) session.getAttribute(CAPTCHA_KEY);
        session.removeAttribute(CAPTCHA_KEY);
        if (StrUtil.isBlank(sessionAttribute)) {
            return false;
        }
        String md5Captcha = SecureUtil.md5(inputRandomCode);
        return md5Captcha.equals(sessionAttribute);
    }

    private static String generateRandonCode(int imgRandNumber) {
        return RandomUtil.randomNumbers(imgRandNumber);
    }

    private static void drawGraphic(BufferedImage image, int imgRandNumber, int imgWidth, int imgHeight, String randonCode) {
        Graphics g = image.createGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, imgWidth, imgHeight);

        for (int i = 0; i < imgRandNumber; i++) {
            g.setColor(Color.BLACK);
            String rand = String.valueOf(randonCode.charAt(i));
            g.drawString(rand, 16 * i + 6, 21);
        }
        g.dispose();
    }
}
