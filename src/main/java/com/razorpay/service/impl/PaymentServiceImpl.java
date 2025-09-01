package com.razorpay.service.impl;

import com.razorpay.dto.PaymentDto;
import com.razorpay.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String createOrder(Double amount, String currency) {
        return null;
    }

    @Override
    @Transactional
    public String verifyPayment(String orderId, String paymentId, String razorpaySignature) {
        return null;
    }

    @Override
    public PaymentDto getPaymentById(String paymentId) {
        return null;
    }

    @Override
    public byte[] downloadReceipt(String transactionNo) {
        return new byte[0];
    }
}
