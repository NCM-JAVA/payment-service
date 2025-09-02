package com.razorpay.client;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Refund;
import com.razorpay.exception.CustomException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentClient {

    private final RazorpayClient razorpayClient;

    public PaymentClient(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    public Payment fetch(String id) {
        try {
            return razorpayClient.payments.fetch(id);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Payment> fetchAll() {
        try {
            return razorpayClient.payments.fetchAll();
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Payment> fetchAll(JSONObject request) {
        try {
            return razorpayClient.payments.fetchAll(request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Payment capture(String id, JSONObject request) {
        try {
            return razorpayClient.payments.capture(id,request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Refund refund(String id){
        try {
            return razorpayClient.payments.refund(id);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Refund refund(String id, JSONObject request){
        try {
            return razorpayClient.payments.refund(id,request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Refund refund(JSONObject request){
        try {
            return razorpayClient.payments.refund(request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Refund fetchRefund(String id, String refundId) {
        try {
            return razorpayClient.payments.fetchRefund(id,refundId);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Refund fetchRefund(String refundId) {
        try {
            return razorpayClient.payments.fetchRefund(refundId);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Refund> fetchAllRefunds(String id, JSONObject request) {
        try {
            return razorpayClient.payments.fetchAllRefunds(id,request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Refund> fetchAllRefunds(String id) {
        try {
            return razorpayClient.payments.fetchAllRefunds(id);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Refund> fetchAllRefunds(JSONObject request) {
        try {
            return razorpayClient.payments.fetchAllRefunds(request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

}
