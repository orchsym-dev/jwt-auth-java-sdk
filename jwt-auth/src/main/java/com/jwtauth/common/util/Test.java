package com.jwtauth.common.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
        OrchsymRSA256KeyGenerator orchsymRSA256KeyGenerator = new OrchsymRSA256KeyGenerator(1024);
        System.out.println(orchsymRSA256KeyGenerator);

        JwtGenerator rs256Token = new JwtGenerator(Base64.getEncoder().encodeToString(orchsymRSA256KeyGenerator.getPrivateKey().getEncoded()), "client", 300, "RS256");
        System.out.println(rs256Token);

        JwtGenerator hs256Token = new JwtGenerator("secretCode", "client", 300, "HS256");
        System.out.println(hs256Token);
    }
}
