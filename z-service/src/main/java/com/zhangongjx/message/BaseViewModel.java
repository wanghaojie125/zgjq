package com.zhangongjx.message;

import com.zhangongjx.message.auth.AuthUser;
import com.zhangongjx.message.auth.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseViewModel {
    private String username;
    private int user_id;
    private int dep_id;

    public BaseViewModel() {
        AuthUser user = (AuthUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (user != null) {
            this.username = user.getUsername();
            this.user_id = user.getUserId();
            this.dep_id = user.getDepId();
        }
    }

    public String getUsername() {
        return username;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getDep_id() {
        return dep_id;
    }
}
