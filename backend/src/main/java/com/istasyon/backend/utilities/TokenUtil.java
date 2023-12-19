package com.istasyon.backend.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.istasyon.backend.entities.User;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenUtil {
    private final String privateKey = "ThisIsaPlaceholderPrivateKeyForTestingPurposes";

    @Value("${custom.cookieName}")
    private String cookieName;

    private final Algorithm algo = Algorithm.HMAC256(privateKey);

    public String encode(User user) {
        var creationTime = Instant.now();

        return JWT.create()
                .withClaim("bool-claim", true)
                .withClaim("email", user.getEmail())
                .withClaim("user-id", user.getUserId())
                .withClaim("datetime-claim",creationTime )
                .withClaim("expire", creationTime.plus(3, ChronoUnit.HOURS))
                .sign(algo);
    }

    public boolean validate(String token) {
        try{
            JWT.require(algo).build().verify(token);
        }
        catch(Exception e){
            return false;
        }

        return true;
    }

    public String getEmail(String token) {
        try{
            return JWT.require(algo).build().verify(token).getClaim("Email").asString();
        }catch (Exception e){
            return "";
        }
    }

    public Cookie encodeCookie(User user){
        String token = this.encode(user);

        Cookie cookie = new Cookie(cookieName,token);
        cookie.setMaxAge(3*60*60);
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        return cookie;
    }
}