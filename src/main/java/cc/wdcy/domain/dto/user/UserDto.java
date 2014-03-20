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
package cc.wdcy.domain.dto.user;

import cc.wdcy.domain.dto.AbstractDto;
import cc.wdcy.domain.user.User;
import cc.wdcy.infrastructure.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class UserDto extends AbstractDto {

    protected String username;
    protected String createDate;

    protected String phone;
    protected String email;
    protected boolean defaultUser;


    public UserDto() {
    }

    public UserDto(User user) {
        super(user.guid());
        this.username = user.username();
        this.createDate = DateUtils.toDateText(user.createTime());

        this.phone = user.phone();
        this.email = user.email();
        this.defaultUser = user.defaultUser();
    }

    public boolean isDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<UserDto> toDtos(List<User> users) {
        List<UserDto> dtoList = new ArrayList<>(users.size());
        for (User user : users) {
            dtoList.add(new UserDto(user));
        }
        return dtoList;
    }
}