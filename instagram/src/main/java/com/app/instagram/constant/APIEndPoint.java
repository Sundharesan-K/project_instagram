package com.app.instagram.constant;

public class APIEndPoint {

    //Admin controller End Points
    public static final String API_ADMIN = "/api_admin";
    public static final String CREATE_ADMIN = "/create";
    public static final String LOGIN_ADMIN = "/login";
    public static final String ADD_ADMIN = "/add";

    //Like controller End Points
    public static final String API_LIKE = "/api_like";

    //Post controller End Points
    public static final String API_POST = "/api_post";
    public static final String CREATE_POST = "/create/{userId}";

    //UserProfile End Points
    public static final String API_USER = "/api_user";
    public static final String CREATE_USER = "/create";
    public static final String LOGIN_USER = "/login";
    public static final String SET_UP_PROFILE = "/set_up_profile/{id}";
    public static final String STATUS_UPDATE = "/status_change/{id}";
}
