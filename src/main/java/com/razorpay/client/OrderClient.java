package com.razorpay.client;

import com.razorpay.Order;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.exception.CustomException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderClient {

    private final RazorpayClient razorpayClient;

    public OrderClient(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    public Order create(JSONObject request){
        try {
            return razorpayClient.orders.create(request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Order> fetchAll() {
        try {
            return razorpayClient.orders.fetchAll();
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Order> fetchAll(JSONObject request){
        try {
            return razorpayClient.orders.fetchAll(request);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Order fetch(String id){
        try {
            return razorpayClient.orders.fetch(id);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

    public List<Payment> fetchPayments(String id){
        try {
            return razorpayClient.orders.fetchPayments(id);
        } catch (RazorpayException e) {
            throw new CustomException(e.getMessage());
        }
    }

}
