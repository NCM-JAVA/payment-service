package com.razorpay.service.impl;

import com.razorpay.Payment;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import com.razorpay.client.OrderClient;
import com.razorpay.client.PaymentClient;
import com.razorpay.config.RazorpayProperties;
import com.razorpay.constant.Constants;
import com.razorpay.dto.PaymentDto;
import com.razorpay.exception.CustomException;
import com.razorpay.service.PaymentService;
import com.razorpay.util.PaymentUtil;
import com.razorpay.util.PropertyUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final Logger logger= LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private RazorpayProperties properties;

    @Override
    public String createOrder(Double amount, String currency) {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100);
        orderRequest.put("currency", currency);
        orderRequest.put("payment_capture", 1);
        JSONObject jsonObject = orderClient.create(orderRequest).toJson();
        jsonObject.put("name", "DEPARTMENT OF SKILL DEVELOPMENT AND ENTREPRENEURSHIP");
        jsonObject.put("description", "ITI Registration Fee");
        jsonObject.put("contact", "+91 8989898989");
        jsonObject.put("email", "abc@mailinator.com");
        jsonObject.put("shipping_address", "xyz");
        jsonObject.put("apiKey", properties.getKeyId());
        jsonObject.put("callback_url", Constants.CALLBACK_URL);
        jsonObject.put("cancel_url", Constants.CANCEL_URL);
        return jsonObject.toString();
    }

    @Override
    @Transactional
    public String verifyPayment(String orderId, String paymentId, String razorpaySignature) {
        String payload = orderId + '|' + paymentId;
        boolean isSignatureVerified = PaymentUtil.verifyingSignature(payload, razorpaySignature, properties.getKeySecrete());
        if (!isSignatureVerified){
            return refundInitiated(paymentId,"Invalid Signature");
        }
        //update payment detail in datasource after verified signature

        return Constants.REDIRECT_URL + paymentId;
    }

    private String refundInitiated(String paymentId, String errorMessage) {
        logger.error(errorMessage);
        Refund refund = paymentClient.refund(paymentId);
        logger.info("refund information: {}",refund);
        return Constants.FAILED_REDIRECT_URL + paymentId;
    }

    @Override
    public PaymentDto getPaymentById(String paymentId) {
        Payment payment = paymentClient.fetch(paymentId);
        return PropertyUtil.mapJsonInto(payment,PaymentDto.class);
    }

    @Override
    public byte[] downloadReceipt(String transactionNo) {
        return new byte[0];
    }
}
