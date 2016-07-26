package com.monkeyk.sos.domain.oauth;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

import static com.monkeyk.sos.infrastructure.CacheConstants.*;

/**
 * 2016/7/26
 *
 * @author Shengzhao Li
 */
public class CustomJdbcTokenStore extends JdbcTokenStore {


    public CustomJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }


    @Cacheable(value = OAUTH2_AUTHENTICATION_CACHE, key = "#token.getValue()")
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return super.readAuthentication(token);
    }


    @Cacheable(value = OAUTH2_AUTHENTICATION_CACHE, key = "#token")
    public OAuth2Authentication readAuthentication(String token) {
        return super.readAuthentication(token);
    }



}
