package com.monkeyk.sos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 2016/4/4
 *
 * @author Shengzhao Li
 */
//  unity-resource
//@Configuration
//@EnableResourceServer
public class UnityResourceServerConfigurer extends ResourceServerConfigurerAdapter {


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

