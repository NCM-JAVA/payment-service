package com.razorpay.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtProperties {
    private final String secreteKey;
    private final long tokenValidity;
    private final String tokenHeader;
    private final String tokenPrefix;

    public JwtProperties(String secreteKey, long tokenValidity, String tokenHeader, String tokenPrefix) {
        this.secreteKey = secreteKey;
        this.tokenValidity = tokenValidity;
        this.tokenHeader = tokenHeader;
        this.tokenPrefix = tokenPrefix;
    }
}
