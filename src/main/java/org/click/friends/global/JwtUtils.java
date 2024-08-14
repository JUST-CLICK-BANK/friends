package org.click.friends.global;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.click.friends.dto.response.UserTokenResponse;
import org.click.friends.entity.Friends;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtUtils {

    private final SecretKey secretKey;

    public UserTokenResponse parseToken(String token) {
        Claims payload = (Claims) Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parse(token)
            .getPayload();

        String myCode = payload.get("code", String.class);
        String myAccount = payload.get("account", String.class);

        return UserTokenResponse.from(myCode, myAccount);
    }

    public JwtUtils(
        @Value("${token.secret}") String secret
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }
}
