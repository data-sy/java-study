package com.jwt.jwtredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static javax.crypto.Cipher.SECRET_KEY;

@Configuration
public class AES128Config {

//    @Bean
//    public TextEncryptor textEncryptor() {
//        return Encryptors.text("your-secret-key", KeyGenerators.string().generateKey());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//    //decryptAes
//        @Bean
//        public String decryptAes(String encryptedData) throws Exception {
//            byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
//            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
//
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.DECRYPT_MODE, originalKey);
//
//            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
//            String decryptedData = new String(decryptedBytes);
//
//            return decryptedData;
//        }
}
