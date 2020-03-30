package com.roje.web.server.utils;

import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author Ro
 */
public class CaptchaUtil {

    private CaptchaUtil() {}

    public static ImageCode genCaptcha() {
        int width = 100, height = 40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(randomColor(200, 255));
        graphics.fillRect(0, 0, width, height);
        graphics.setFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        graphics.setColor(randomColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = RandomUtils.nextInt(0, width);
            int y = RandomUtils.nextInt(0, height);
            int xl = RandomUtils.nextInt(0, 12);
            int yl = RandomUtils.nextInt(0, 12);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(RandomUtils.nextInt(0, 10));
            sRand.append(rand);
            graphics.setColor(new Color(20 + RandomUtils.nextInt(0, 110), 20 + RandomUtils.nextInt(0,110),
                    20 + RandomUtils.nextInt(0, 110)));
            float x = i * width * 1.0f / 4;
            graphics.drawString(rand, (int) x, height - 8);
        }

        graphics.dispose();
        return ImageCode.builder()
                .code(sRand.toString())
                .image(image)
                .build();
    }

    public static Color randomColor(int min, int max) {

        int r = RandomUtils.nextInt(min, max);
        int g = RandomUtils.nextInt(min, max);
        int b = RandomUtils.nextInt(min, max);
        return new Color(r, g, b);
    }
}
