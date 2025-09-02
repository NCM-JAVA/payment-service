package com.razorpay.constant;

public class Constants {
    public static final String FAILED_REDIRECT_URL = "http://127.0.0.1:8080/PaymentFailed"; // front-end url
    public static final String REDIRECT_URL = "http://127.0.0.1:8080/paymentSuccess/"; // front-end url
    public static final String CALLBACK_URL = "http://127.0.0.1:8080/api/v1/payment/verify"; // backend url
    public static final String CANCEL_URL = "http://127.0.0.1:8080/"; // front-end url

    private Constants(){}
}
