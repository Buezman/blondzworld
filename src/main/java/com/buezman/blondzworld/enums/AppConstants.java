package com.buezman.blondzworld.enums;

import org.springframework.beans.factory.annotation.Value;

public class AppConstants {
    public static final String BASE_URL = "api/v1";
    public static final String JWT_SECRET = "taijutsu1234";
    public static final int JWT_EXP_TIME = 15 * 60 * 1000;

    @Value("${}")
    public String CLOUDINARY_URL;
}
