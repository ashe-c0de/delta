package org.ashe.delta.infra;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.ashe.delta.infra.exp.BusinessException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TokenParse {
    private static final String SIGN_KEY = "aELMJjqWxZGS4gdqlO";

    private TokenParse(){}

    /**
     * 获取token
     */
    public static String getJWTToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String authorization = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(authorization)) {
            return null;
        }
        return authorization.substring("Bearer ".length());
    }

    /**
     * 解析token
     */
    private static Claims getClaims(String jwtToken) {
        if (ObjectUtils.isEmpty(jwtToken)) {
            throw new BusinessException("token异常，请重新登录");
        }
        return Jwts.parser()
                .setSigningKey(SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    /**
     * 获取当前用户工号
     */
    public static String getUserCode() {
        Claims claims = getClaims(getJWTToken());
        return (String) claims.get("u_code");
    }


}
