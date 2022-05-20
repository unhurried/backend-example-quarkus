package io.github.unhurried.example.backend.quarkus;

import java.util.Arrays;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class TestHelper {

    public String createToken() {
        var secret = Arrays.copyOf("secret".getBytes(), 256/8);
        return Jwt.issuer("http://localhost:3002")
            .claim("scope", "todo")
            .expiresAt(new Date().getTime() + 10000)
            .audience("todo-api")
            .subject("123")
            .sign(new SecretKeySpec(secret, "AES")).toString();
    }
}
