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
package cc.honyee.infrastructure;

import org.apache.commons.lang.StringUtils;

/**
 * @author Shengzhao Li
 */
public abstract class MatchUtils {

    /**
     * BigDecimal regex.
     */
    public static final String BIG_DECIMAL_REGEX = "^(\\d+)||(\\d+\\.\\d+)$";

    /**
     * Positive Number regex.
     */
    public static final String POSITIVE_NUMBER_REGEX = "^\\d+$";

    /**
     * Email regex.
     */
    public static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

    public static boolean isBigDecimal(String text) {
        return StringUtils.isNotEmpty(text) && text.matches(BIG_DECIMAL_REGEX);
    }

    public static boolean isEmail(String email) {
        return StringUtils.isNotEmpty(email) && email.matches(EMAIL_REGEX);
    }

    public static boolean isPositiveNumber(String numberText) {
        return StringUtils.isNotEmpty(numberText) && numberText.matches(POSITIVE_NUMBER_REGEX);
    }
}