package com.mbtiapp.mbtiservice.controller;

import com.mbtiapp.mbticommon.Req.TwilioUser;
import com.mbtiapp.mbticommon.Req.TwilioUserReq;
import com.mbtiapp.mbticommon.util.OTPGenerator;
import com.mbtiapp.mbtiservice.service.OtpService;
import com.mbtiapp.mbtiservice.service.TwilioService;
import com.twilio.rest.chat.v1.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/twilio")
public class TwilioController {

    @Autowired
    TwilioService twilioService;
    @Autowired
    OtpService optService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody TwilioUserReq request) {

        String phoneNumber = request.getPhoneNumber();
        twilioService.addPhoneNumber(request.getPhoneNumber());
        // Generate and store OTP
        String otp = OTPGenerator.generateOTP(6);
        TwilioUser user = new TwilioUser(phoneNumber, otp);

        // Send OTP via Twilio SMS
        twilioService.sendSMS(phoneNumber, "Nesfuta Buko why nots : " + otp);

        return ResponseEntity.ok("User created successfully");
    }

    public ResponseEntity<String> verifyUser(@RequestBody TwilioUser request) {
        String phoneNumber = request.getPhoneNumber();
        String otp = request.getOtp();

        // Retrieve user by phone number

        // Mark user as verified

        return ResponseEntity.ok("User verified successfully");
    }

    @RequestMapping(value = "requestOtp/{phoneNo}",method = RequestMethod.GET)
    public Map<String,Object> getOtp(@PathVariable String phoneNo) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            //generate OTP
            String otp = optService.generateOtp(phoneNo);
            returnMap.put("otp", otp);
            returnMap.put("status", "success");
            returnMap.put("message", "Otp sent successfully");
        } catch (Exception e) {
            returnMap.put("status", "failed");
            returnMap.put("message", e.getMessage());
        }

        return returnMap;
    }
}
