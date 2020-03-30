package com.roje.web.server.configuration.security;

import com.roje.web.server.common.ResultCode;
import com.roje.web.server.common.dto.LoginSuccessDto;
import com.roje.web.server.utils.HttpUtils;
import org.springframework.security.web.DefaultRedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ro
 */
public class CustomRedirectStrategy extends DefaultRedirectStrategy {

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response,
                             String url) throws IOException {
        String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
        redirectUrl = response.encodeRedirectURL(redirectUrl);

        if (logger.isDebugEnabled()) {
            logger.debug("Redirecting to '" + redirectUrl + "'");
        }

        if (HttpUtils.isAjaxRequest(request)) {
            LoginSuccessDto loginSuccessDto = new LoginSuccessDto();
            loginSuccessDto.setRedirectUrl(redirectUrl);
            HttpUtils.sendJsonData(ResultCode.SUCCESS, redirectUrl, response);
        } else {
            response.sendRedirect(redirectUrl);
        }
    }

}
