package com.github.w4o.xx.applet.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.github.w4o.xx.applet.common.LoginUser;
import com.github.w4o.xx.applet.common.config.AppConfig;
import com.github.w4o.xx.core.exception.CustomException;
import com.github.w4o.xx.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUtils {

    private final AppConfig appConfig;

    private static final String CLAIM_KEY_OPEN_ID = "open_id";
    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_USERNAME = "sub";

    public String generateToken(LoginUser loginUser) {
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, appConfig.getJwt().getExpire());
        Date expiresDate = nowTime.getTime();

        Date iatDate = new Date();

        // header Map
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        return JWT.create()
                .withHeader(map)
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .withClaim(CLAIM_KEY_OPEN_ID, loginUser.getOpenId())
                .withClaim(CLAIM_KEY_USER_ID, loginUser.getUserId())
                .sign(Algorithm.HMAC256(appConfig.getJwt().getSecret()));
    }

    public LoginUser getLoginUserFromToken(String token) {
        Map<String, Claim> map = getClaimsFromToken(token);
        return LoginUser.builder()
                .openId(map.get(CLAIM_KEY_USERNAME).asString())
                .userId(map.get(CLAIM_KEY_USER_ID).asLong())
                .build();
    }


    private Map<String, Claim> getClaimsFromToken(String token) {
        String realToken = token.replace("Bearer ", "");

        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(appConfig.getJwt().getSecret()))
                    .acceptLeeway(30)
                    .build();
            jwt = verifier.verify(realToken);
        } catch (TokenExpiredException e) {
            throw new CustomException(ErrorCode.E402);
        } catch (Exception e) {
            // token 校验失败, 抛出Token验证非法异常
            throw new CustomException(ErrorCode.E401);
        }
        return jwt.getClaims();
    }
}
