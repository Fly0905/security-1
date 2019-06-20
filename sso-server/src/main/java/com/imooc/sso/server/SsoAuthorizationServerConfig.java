/**
 * 
 */
package com.imooc.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author zhailiang
 * @EnableAuthorizationServer 表明这是个Oauth2认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 配置认证服务器，能给哪些应用发令牌
     * @param clients
     * @throws Exception
     */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("imooc1")
				.secret("imoocsecrect1")
				.authorizedGrantTypes("authorization_code", "refresh_token")
				.scopes("all")
				.and()
				.withClient("imooc2")
				.secret("imoocsecrect2")
				.authorizedGrantTypes("authorization_code", "refresh_token")
				.scopes("all");
	}

    /**
     *
     * @param endpoints
     * @throws Exception
     */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
	}

    /**
     * 认证服务器的安全配置
     * @param security
     * @throws Exception
     */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	    // 访问tokenKey（jwt的密钥）需要经过身份认证
		security.tokenKeyAccess("isAuthenticated()");
	}

    /**
     *
     * @return
     */
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

    /**
     * 配置Token的密钥
     * @return
     */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("imooc");
        return converter;
	}

}
