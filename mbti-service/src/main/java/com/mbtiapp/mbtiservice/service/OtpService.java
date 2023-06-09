package com.mbtiapp.mbtiservice.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mbtiapp.mbtiservice.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
    public class OtpService {
        private static final  Integer EXPIRE_MIN = 5;
        private LoadingCache<String,String> otpCache;
        @Autowired
        private TwilioConfig twilioConfig;

        public OtpService() {
            otpCache = CacheBuilder.newBuilder()
                    .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                    .build(new CacheLoader<>() {
                        @Override
                        public String load(String s) {
                            return "";
                        }
                    });
        }

        public String generateOtp(String phoneNo){
            PhoneNumber to = new PhoneNumber(phoneNo);
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = getRandomOTP(phoneNo);
            String otpMessage = "Dear Customer , Your OTP is " + otp + ". Use this otp to log in to Rapido Clone Application";
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

            Message.creator(
                    new PhoneNumber(phoneNo),
                    new PhoneNumber(twilioConfig.getTrialNumber()),
                    ""
            ).setMessagingServiceSid(twilioConfig.getAccountSid()).create();
            return otp;
        }

        private String getRandomOTP(String phoneNo) {
            String otp =  new DecimalFormat("000000")
                    .format(new Random().nextInt(999999));
            otpCache.put(phoneNo,otp);
            return otp;
        }
        //get saved otp
        public String getCacheOtp(String key){
            try{
                return otpCache.get(key);
            }catch (Exception e){
                return "";
            }
        }
        //clear stored otp
        public void clearOtp(String key){
            otpCache.invalidate(key);
        }
}
