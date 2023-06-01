package com.mbtiapp.mbticommon.util;

import java.util.Random;

public class OTPGenerator {
    private static final String ALLOWED_CHARACTERS = "0123456789";

    public static String generateOTP(int length) {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            otp.append(ALLOWED_CHARACTERS.charAt(index));
        }

        return otp.toString();
    }
}
