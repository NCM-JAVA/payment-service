package com.razorpay.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@ConfigurationProperties(prefix = "razorpay.properties")
public class RazorpayProperties {
    private final String keyId;
    private final String keySecrete;

    public RazorpayProperties(String keyId, String keySecrete) {
        this.keyId = keyId;
        this.keySecrete = keySecrete;
    }
}
