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
package cc.honyee.web.validator;

import cc.honyee.domain.dto.user.UserFormDto;
import cc.honyee.infrastructure.MatchUtils;
import cc.honyee.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Shengzhao Li
 */
@Component
public class UserFormDtoValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserFormDto.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormDto userFormDto = (UserFormDto) target;
        validateUsername(userFormDto, errors);
        validateEmail(userFormDto, errors);

        if (userFormDto.isNewly()) {
            validatePassword(userFormDto, errors);
        }
    }

    private void validatePassword(UserFormDto userFormDto, Errors errors) {
        String password = userFormDto.getPassword();
        if (password.length() < 6) {
            errors.rejectValue("password", null, "用户密码至少6位");
            return;
        }
        if (!password.equals(userFormDto.getRePassword())) {
            errors.rejectValue("rePassword", null, "两次输入的密码不相同");
        }

    }

    private void validateEmail(UserFormDto userFormDto, Errors errors) {
        String email = userFormDto.getEmail();
        if (!MatchUtils.isEmail(email)) {
            errors.rejectValue("email", null, "邮件地址格式错误");
        }
    }

    private void validateUsername(UserFormDto userFormDto, Errors errors) {
        String username = userFormDto.getUsername();
        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("username", null, "用户名不能为空");
            return;
        }
        if (!username.equals(userFormDto.getExistUsername())) {
//            boolean exist = userService.loadExistUsername(username);
//            if (exist) {
//                errors.rejectValue("username", null, "该用户名已经存在");
//            }
        }
    }
}