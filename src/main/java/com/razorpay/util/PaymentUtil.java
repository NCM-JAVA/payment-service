package com.razorpay.util;

import com.razorpay.exception.CustomException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class PaymentUtil {

    private PaymentUtil(){}

    public static boolean verifyingSignature(String payload, String razorpaySignature, String razorpayKeySecret) {
        String actualSignature = calculateRFC2104HMAC(payload, razorpayKeySecret);
        return actualSignature.equals(razorpaySignature);
    }

    private static String calculateRFC2104HMAC(String payload, String razorpayKeySecret) {
        try{
            SecretKeySpec signingKey = new SecretKeySpec(razorpayKeySecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return new String(Hex.encodeHex(rawHmac));
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
