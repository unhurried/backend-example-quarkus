package io.github.unhurried.example.backend.quarkus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;

import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class TestHelper {

    public String createToken() {
        var secret = Arrays.copyOf("secret".getBytes(), 256/8);
        long now = ZonedDateTime.now(ZoneId.of("UTC")).toEpochSecond();
        return Jwt.issuer("http://localhost:3002")
            .claim("scope", "todo")
            .expiresAt(now + 10000)
            .audience("todo-api")
            .subject("123")
            .sign(new SecretKeySpec(secret, "AES")).toString();
    }

    public String createExpiredToken() {
        var secret = Arrays.copyOf("secret".getBytes(), 256/8);
        long now = ZonedDateTime.now(ZoneId.of("UTC")).toEpochSecond();
        return Jwt.issuer("http://localhost:3002")
            .claim("scope", "todo")
            .expiresAt(now - 10000)
            .audience("todo-api")
            .subject("123")
            .sign(new SecretKeySpec(secret, "AES")).toString();
    }
}
