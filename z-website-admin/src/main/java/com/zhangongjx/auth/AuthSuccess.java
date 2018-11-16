package com.zhangongjx.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangongjx.model.l.LogLoginEntity;
import com.zhangongjx.repository.l.ILogLoginRepository;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class AuthSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private ILogLoginRepository logLoginRepository;
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        //记录登陆成功日志
        LogLoginEntity item = new LogLoginEntity();
        item.setBrowser(request.getHeader("User-Agent"));
        item.setArea("");
        item.setIp(request.getRemoteAddr());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        item.setLogin_at(date.toString());
        item.setType(1);
        item.setUser_id(User.getUser_id());

        logLoginRepository.insert(item);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(MAPPER.writeValueAsString(ResutlMsg.success()));
    }
}
