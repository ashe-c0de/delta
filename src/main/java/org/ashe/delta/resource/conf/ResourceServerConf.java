package org.ashe.delta.resource.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * 资源服务器配置
 */
@Configuration
public class ResourceServerConf extends ResourceServerConfigurerAdapter {

    @Resource
    private TokenStore tokenStore;

//    private static final String RESOURCE_ID = "app";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                // 资源id
//                .resourceId(RESOURCE_ID)
                // 使用远程服务验证token
//                .tokenServices(tokenServices())
                // 配合授权服务器提供的私钥自己验证token
                .tokenStore(tokenStore)
                // 无状态模式
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 需要校验的路径
                .antMatchers("/token/**")
                // 需要匹配scope
                .access("#oauth2.hasScope('all')")
                .antMatchers("/access/**")
                .permitAll()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}