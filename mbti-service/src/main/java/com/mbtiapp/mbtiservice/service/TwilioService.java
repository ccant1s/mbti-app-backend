package com.mbtiapp.mbtiservice.service;


import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumber;
import com.twilio.rest.api.v2010.account.IncomingPhoneNumberCreator;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;

@Service
public class TwilioService {

    private final TwilioConfiguration twilioConfiguration;
    private final TwilioRestClient twilioRestClient;
    public static final String ACCOUNT_SID = System.getenv("AC3e5d10c0354c6b3299b8ac74f2896799");
    public static final String AUTH_TOKEN = System.getenv("4360baa1218c0820ebae0b75adb0e89f");

    @Autowired
    public TwilioService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;

        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
        this.twilioRestClient = new TwilioRestClient.Builder(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        ).build();
    }

    public void sendSMS (String phoneNumber, String message){
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioConfiguration.getPhoneNumber()),
                message
        ).setMessagingServiceSid(twilioConfiguration.getMessagingServiceSid()).create();
    }

    public void addPhoneNumber(String phoneNumber) {
        IncomingPhoneNumberCreator creator = IncomingPhoneNumber.creator(new PhoneNumber(phoneNumber));
        IncomingPhoneNumber incomingPhoneNumber = creator.create(twilioRestClient);

        // Verify the phone number
        String phoneNumberSid = incomingPhoneNumber.getSid();
        String url = Domains.API.toString() + "/2010-04-01/Accounts/" + twilioConfiguration.getAccountSid() +
                "/IncomingPhoneNumbers/" + phoneNumberSid + ".json";

        Request request = new Request(HttpMethod.POST, url);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("Status", "verified"));
        request.addPostParam("Status", "verified");

        try {
            Response response = twilioRestClient.request(request);
        } catch (Exception e) {
        }

    }

}
