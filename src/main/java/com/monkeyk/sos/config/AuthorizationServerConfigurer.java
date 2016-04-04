package com.monkeyk.sos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * 2016/4/4
 *
 * @author Shengzhao Li
 */
//AuthorizationServer
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private DefaultTokenServices tokenServices;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private OAuth2AccessDeniedHandler oauth2AccessDeniedHandler;
    @Autowired
    private OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userApprovalHandler(userApprovalHandler)
//                .tokenServices(tokenServices)
                .authorizationCodeServices(authorizationCodeServices);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.accessDeniedHandler(oauth2AccessDeniedHandler)
                .authenticationEntryPoint(oAuth2AuthenticationEntryPoint)
                .allowFormAuthenticationForClients();
        security.realm("spring-oauth-server_realm");
    }


}
