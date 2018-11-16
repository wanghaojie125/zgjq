package com.zhangongjx.util;

import com.zhangongjx.message.auth.request.SaveUserRequest;
import org.springframework.util.StringUtils;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/10/18 15:56
 */
public class ValidateUtil {
    public static String registerValidate(SaveUserRequest request){
        if(StringUtils.isEmpty(request.getUsername())){
            return "用户名不能为空";
        }
        if(StringUtils.isEmpty(request.getPwd())){
            return "密码不能为空";
        }
        if(StringUtils.isEmpty(request.getEmail())){
            return "邮箱不能为空";
        }
        if(StringUtils.isEmpty(request.getPhone())){
            return "手机不能为空";
        }
        if(StringUtils.isEmpty(request.getAuthenticode())){
            return "验证码不能为空";
        }
        return null;
    }
}
