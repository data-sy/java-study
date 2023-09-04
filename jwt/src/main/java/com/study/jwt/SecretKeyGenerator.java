//package com.study.jwt;
//
//import java.security.SecureRandom;
//import java.util.Base64;
//
//public class SecretKeyGenerator {
//    public static void main(String[] args) {
//
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] secretKeyBytes = new byte[32]; // 256 bits
//        secureRandom.nextBytes(secretKeyBytes);
//
//        // 시크릿 키를 Base64 인코딩하여 출력
//        String secretKey = Base64.getEncoder().encodeToString(secretKeyBytes);
//        System.out.println("Generated Secret Key: " + secretKey);
//    }
//}
