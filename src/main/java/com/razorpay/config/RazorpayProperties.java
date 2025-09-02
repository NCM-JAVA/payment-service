package com.razorpay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "razorpay.properties")
public class RazorpayProperties {
    private String keyId;
    private String keySecrete;
}
