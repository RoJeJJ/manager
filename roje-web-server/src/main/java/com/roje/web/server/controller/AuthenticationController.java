package com.roje.web.server.controller;

import com.roje.web.server.configuration.WebProperties;
import com.roje.web.server.utils.CaptchaUtil;
import com.roje.web.server.utils.ImageCode;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Ro
 */
@RestController
public class AuthenticationController {

    public static final int DEFAULT_EXPIRE_SEC = 5;

    private final WebProperties webProperties;

    public AuthenticationController(WebProperties webProperties) {
        this.webProperties = webProperties;
    }

    @GetMapping("/captcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode image = CaptchaUtil.genCaptcha();
        image.setExpireDt(LocalDateTime.now().plusSeconds(getSecond()));
        request.getSession().setAttribute(webProperties.getCaptchaAttrName(), image);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ImageIO.write(image.getImage(), "JPEG", response.getOutputStream());
    }

    private int getSecond() {
        int sec = webProperties.getCaptchaExpiredSec();
        if (sec <= DEFAULT_EXPIRE_SEC) {
            sec = DEFAULT_EXPIRE_SEC;
        }
        return sec;
    }
}
