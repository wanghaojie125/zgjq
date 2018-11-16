package com.zhangongjx.auth;

import com.zhangongjx.message.auth.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class User {
    private  static String username;
    private  static int user_id;
    private static int dep_id;
    static {
        AuthUser user = (AuthUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (user != null) {
            username = user.getUsername();
            user_id = user.getUserId();
            dep_id = user.getDepId();
        }
    }

    public static String getUsername() {
        return username;
    }

    public static int getUser_id() {
        return user_id;
    }

    public static int getDep_id() {
        return dep_id;
    }
}
