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
package cc.wdcy.infrastructure;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @author Shengzhao Li
 */
public abstract class PasswordHandler {


    /**
     * Encrypt  password ,if original password is empty,
     *
     * @param originalPassword Original password
     * @return Encrypted password
     */
    public static String encryptPassword(String originalPassword) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        return md5PasswordEncoder.encodePassword(originalPassword, null);
    }


}