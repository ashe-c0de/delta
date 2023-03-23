package org.ashe.delta.resource.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class BeanConf {

    /**
     * 与授权服务器的公钥签名保持一致
     */
    private static final String SIGN_KEY = "aELMJjqWxZGS4gdqlO";

    /**
     * 注入自定义配置的tokenStore
     */
    @Bean
    public TokenStore tokenStore(){
        // jwt令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 使用和认证服务器统一的公钥签名
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGN_KEY);
        return jwtAccessTokenConverter;
    }
}