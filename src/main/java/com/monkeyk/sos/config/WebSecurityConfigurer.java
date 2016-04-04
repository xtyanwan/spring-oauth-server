package com.monkeyk.sos.config;

import com.monkeyk.sos.domain.oauth.CustomJdbcClientDetailsService;
import com.monkeyk.sos.service.OauthService;
import com.monkeyk.sos.service.UserService;
import com.monkeyk.sos.web.oauth.OauthUserApprovalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.vote.ScopeVoter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * 2016/4/3
 * <p/>
 * Replace security.xml
 *
 * @author Shengzhao Li
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.expressionHandler(new OAuth2WebSecurityExpressionHandler());
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    @Bean(name = "authenticationManager")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/oauth/**").hasAnyRole("ROLE_USER,ROLE_UNITY,ROLE_MOBILE")
                .antMatchers("/**").anonymous()
                .and()
                .exceptionHandling().accessDeniedPage("/login.jsp?authorization_error=2")
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login.jsp")
                .failureUrl("/login.jsp?authentication_error=1")
                .defaultSuccessUrl("/index.jsp")
                .loginProcessingUrl("/login.do")
                .and()
                .logout().logoutUrl("/logout.do")
                .logoutSuccessUrl("/index.jsp")
                .and()
                .anonymous();


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new Md5PasswordEncoder());
    }

    /*
    * OAuth2 Configuration start
    * */

    @Bean(name = "clientDetailsService")
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        return new CustomJdbcClientDetailsService(dataSource);
    }


    @Bean(name = "tokenStore")
    public TokenStore tokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }


    @Bean(name = "tokenServices")
    public DefaultTokenServices tokenServices(TokenStore tokenStore, ClientDetailsService clientDetailsService) {
        final DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setClientDetailsService(clientDetailsService);
        tokenServices.setSupportRefreshToken(true);
        return tokenServices;
    }

    @Bean(name = "oAuth2RequestFactory")
    public OAuth2RequestFactory oAuth2RequestFactory(ClientDetailsService clientDetailsService) {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }


    @Bean(name = "oauthUserApprovalHandler")
    public UserApprovalHandler oauthUserApprovalHandler(TokenStore tokenStore, ClientDetailsService clientDetailsService, OAuth2RequestFactory oAuth2RequestFactory, OauthService oauthService) {
        OauthUserApprovalHandler userApprovalHandler = new OauthUserApprovalHandler();
        userApprovalHandler.setTokenStore(tokenStore);
        userApprovalHandler.setClientDetailsService(clientDetailsService);
        userApprovalHandler.setRequestFactory(oAuth2RequestFactory);
        userApprovalHandler.setOauthService(oauthService);
        return userApprovalHandler;
    }


    @Bean(name = "jdbcAuthorizationCodeServices")
    public AuthorizationCodeServices jdbcAuthorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    @Bean(name = "oauth2AuthenticationEntryPoint")
    public OAuth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {
        return new OAuth2AuthenticationEntryPoint();
    }


    @Bean(name = "oauth2ClientDetailsUserService")
    public ClientDetailsUserDetailsService oauth2ClientDetailsUserService(ClientDetailsService clientDetailsService) {
        return new ClientDetailsUserDetailsService(clientDetailsService);
    }


    @Bean(name = "oauth2AuthenticationManager")
    public AuthenticationManager oauth2AuthenticationManager(ClientDetailsUserDetailsService detailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detailsService);
        List<AuthenticationProvider> providers = Arrays.asList(daoAuthenticationProvider);
        return new ProviderManager(providers);
    }


    @Bean(name = "oauth2AccessDecisionManager")
    public UnanimousBased oauth2AccessDecisionManager() {
        return new UnanimousBased(Arrays.asList(
                new ScopeVoter(),
                new RoleVoter(),
                new AuthenticatedVoter()));
    }


    @Bean(name = "oauth2AccessDeniedHandler")
    public OAuth2AccessDeniedHandler oauth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }


    @Bean(name = "clientCredentialsTokenEndpointFilter")
    public ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter(AuthenticationManager oauth2AuthenticationManager) {
        ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter = new ClientCredentialsTokenEndpointFilter();
        clientCredentialsTokenEndpointFilter.setAuthenticationManager(oauth2AuthenticationManager);
        return clientCredentialsTokenEndpointFilter;
    }




}
