package com.razorpay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtProperties {
    private String secreteKey;
    private long tokenValidity;
    private String tokenHeader;
    private String tokenPrefix;
}
