package com.roje.web.server.configuration.security;

import com.roje.web.server.common.exception.CaptchaException;
import com.roje.web.server.configuration.WebProperties;
import com.roje.web.server.utils.ImageCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author Ro
 */
@Slf4j
public class CaptchaFilter extends OncePerRequestFilter {

    private final WebProperties webProperties;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    public CaptchaFilter(WebProperties webProperties, AuthenticationFailureHandler authenticationFailureHandler) {
        this.webProperties = webProperties;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (webProperties.getLoginProcessUrl().equals(request.getRequestURI()) && request.getMethod().equals(HttpMethod.POST.name())) {
            try {
                checkCaptcha(request);
            } catch (CaptchaException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        doFilter(request, response, filterChain);
    }


    private void checkCaptcha(HttpServletRequest request) throws CaptchaException, ServletException {
        String captcha = ServletRequestUtils.getStringParameter(request, webProperties.getCaptchaParameterName());
        ImageCode image = (ImageCode) request.getSession().getAttribute("captcha");
        if (image == null) {
            throw new CaptchaException("还未生成验证码");
        } else {
            if (image.getExpireDt().isBefore(LocalDateTime.now())) {
                throw new CaptchaException("验证码过期");
            } else {
                String code = image.getCode();
                log.debug("正确的验证码:{},客户端传过来的验证码:{}", code, captcha);
                if (StringUtils.isBlank(code) || !StringUtils.equalsIgnoreCase(code, captcha)) {
                    throw new CaptchaException("验证码校验失败");
                }
            }
        }
    }
}
