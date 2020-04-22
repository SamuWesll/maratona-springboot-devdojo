package br.com.devdojo.awesome;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

//    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123"));
//    }

    public String cryptPasswordEncoder(String senha) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String crypto = passwordEncoder.encode(senha);
        return crypto;
    }

}
