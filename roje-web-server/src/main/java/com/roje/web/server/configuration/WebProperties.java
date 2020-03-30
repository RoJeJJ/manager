package com.roje.web.server.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Ro
 */
@ConfigurationProperties(prefix = "roje.web")
@Getter@Setter
public class WebProperties {

    private String loginProcessUrl = "/session";

    private String captchaParameterName = "captcha";

    private String captchaAttrName = "captcha";

    private int captchaExpiredSec = 60;
}
