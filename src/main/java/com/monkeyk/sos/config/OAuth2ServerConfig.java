package com.monkeyk.sos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 2016/4/4
 *
 * @author Shengzhao Li
 */
@Configuration
public class OAuth2ServerConfig {


    //  unity-resource
    @Configuration
    @EnableResourceServer
    protected static class UnityResourceServerConfigurer extends ResourceServerConfigurerAdapter {


        @Autowired
        private AccessDecisionManager oauth2AccessDecisionManager;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId("unity-resource").stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
//        final DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
//        expressionHandler.setExpressionParser();

            http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                    .and()
                    .requestMatchers().antMatchers("/unity/**")
                    .and()
                    .authorizeRequests()
//                .expressionHandler(expressionHandler)
                    .antMatchers("/unity/**")
//                .access("hasRole('ROLE_UNITY') and hasRole('SCOPE_READ')")
                    .access("#oauth2.clientHasRole('ROLE_UNITY') and #oauth2.isClient() and #oauth2.hasScope('read')")
                    .accessDecisionManager(oauth2AccessDecisionManager)
                    .and().csrf().disable();

        }

    }


    //AuthorizationServer
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

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
//        @Autowired
//        private OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint;

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
//                    .authenticationEntryPoint(oAuth2AuthenticationEntryPoint)
                    .allowFormAuthenticationForClients();
            security.realm("spring-oauth-server_realm");
        }


    }


}
