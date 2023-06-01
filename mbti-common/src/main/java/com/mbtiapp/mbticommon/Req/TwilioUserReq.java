package com.mbtiapp.mbticommon.Req;

import lombok.Data;

@Data
public class TwilioUserReq {
    private String phoneNumber;

    public TwilioUserReq() {
    }

    public TwilioUserReq(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
