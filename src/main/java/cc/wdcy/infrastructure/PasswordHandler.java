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

import cc.wdcy.domain.shared.GuidGenerator;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.util.StringUtils;

/**
 * @author Shengzhao Li
 */
public abstract class PasswordHandler {


    /**
     * Encrypt factor, use it for  reversible  password
     */
    private static final int ENCRYPT_FACTOR = 7;

    /**
     * Return a random password from {@link java.util.UUID},
     * the length is 8.
     *
     * @return Random password
     */
    public static String randomPassword() {
        String uuid = GuidGenerator.generate();
        return uuid.substring(0, 8);
    }

    /**
     * Encrypt  password ,if original password is empty,
     * will call {@link #randomPassword()}  get a random original password.
     *
     * @param originalPassword Original password
     * @return Encrypted password
     */
    public static String encryptPassword(String originalPassword) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        return md5PasswordEncoder.encodePassword(originalPassword, null);
    }

    /**
     * Encrypt the reversible password
     *
     * @param originalPassword originalPassword
     * @return encrypted password
     */
    public static String encryptReversiblePassword(String originalPassword) {
        if (!StringUtils.hasText(originalPassword)) {
            return originalPassword;
        }
        byte[] bytes = originalPassword.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            bytes[i] = (byte) (b ^ ENCRYPT_FACTOR);
        }
        return new String(bytes);
    }

    /**
     * Decrypt the encrypted password.
     *
     * @param encryptedPassword encryptedPassword
     * @return decrypted password
     */
    public static String decryptReversiblePassword(String encryptedPassword) {
        return encryptReversiblePassword(encryptedPassword);
    }
}