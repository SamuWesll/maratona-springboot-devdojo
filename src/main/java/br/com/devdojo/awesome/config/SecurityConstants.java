package br.com.devdojo.awesome.config;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

    static final String SECRET = "DevDojo";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_PREFIX = "Authorization ";
    static final long EXPIRATION_TIME = 86400000l;

//    public static void main(String[] args) {
//        System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
//    }

}
