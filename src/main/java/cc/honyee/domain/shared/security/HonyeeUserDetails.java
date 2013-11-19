/*
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
 */
package cc.honyee.domain.shared.security;

import cc.honyee.infrastructure.DateUtils;
import cc.honyee.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Shengzhao Li
 */
public class HonyeeUserDetails implements UserDetails {

    protected static final String ROLE_PREFIX = "ROLE_";
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + "USER");
    protected static final GrantedAuthority ADMIN_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN");

    protected User user;


    public HonyeeUserDetails() {
    }

    public HonyeeUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if (user.defaultUser()) {
//            return Arrays.asList(ADMIN_USER_ROLE, DEFAULT_USER_ROLE);
//        }
        return Arrays.asList(DEFAULT_USER_ROLE, new SimpleGrantedAuthority(ROLE_PREFIX + "UNITY"), new SimpleGrantedAuthority(ROLE_PREFIX + "MOBILE"));
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User user() {
        return user;
    }

    public String getLastLoginTime() {
        if (user != null && user.lastLoginTime() != null) {
            return DateUtils.toDateText(user.lastLoginTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT);
        }
        return "---";
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}