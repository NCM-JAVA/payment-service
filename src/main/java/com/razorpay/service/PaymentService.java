package com.razorpay.service;

import com.razorpay.dto.PaymentDto;

public interface PaymentService {
    String createOrder(Double amount, String currency);

    String verifyPayment(String orderId, String paymentId, String razorpaySignature);

    PaymentDto getPaymentById(String paymentId);

    byte[] downloadReceipt(String transactionNo);
}
