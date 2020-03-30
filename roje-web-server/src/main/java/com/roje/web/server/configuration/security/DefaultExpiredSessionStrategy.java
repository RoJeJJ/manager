package com.roje.web.server.configuration.security;

import com.roje.web.server.common.ResultCode;
import com.roje.web.server.utils.HttpUtils;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ro
 */
public class DefaultExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    public DefaultExpiredSessionStrategy() {
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        Assert.notNull(response, "HttpServletResponse required");
        HttpUtils.sendJsonData(ResultCode.SESSION_EXPIRED, response);
    }
}
