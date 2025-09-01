package com.razorpay.controller;

import com.razorpay.exception.CustomException;
import com.razorpay.service.PaymentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestParam Double amount){
        return ResponseEntity.ok(paymentService.createOrder(amount,"INR"));
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestParam String razorpay_payment_id,@RequestParam String razorpay_order_id,@RequestParam String razorpay_signature){
        String redirectUrl = paymentService.verifyPayment(razorpay_order_id, razorpay_payment_id, razorpay_signature);
        return ResponseEntity.status(302).header("Location", redirectUrl)
                .body("Redirecting to " + redirectUrl);
    }

    @GetMapping("/getPayment/{paymentId}")
    public ResponseEntity<?> getPayment(@PathVariable String paymentId){
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @GetMapping("/downloadReceipt/{transactionNo}")
    public ResponseEntity<?> downloadReceipt(@PathVariable String transactionNo) {
        if (transactionNo==null || transactionNo.isEmpty()){
            throw new CustomException("Application number is not found");
        }
        byte[] bytes = paymentService.downloadReceipt(transactionNo);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline; filename=" + transactionNo+".pdf");
        return ResponseEntity.status(HttpStatus.OK).headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
