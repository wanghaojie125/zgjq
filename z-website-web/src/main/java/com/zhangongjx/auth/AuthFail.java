package com.zhangongjx.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Component
public class AuthFail implements AuthenticationFailureHandler {
        private Logger logger = LoggerFactory.getLogger(getClass());

        private static final ObjectMapper MAPPER = new ObjectMapper();

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws UsernameNotFoundException, IOException, ServletException {
                response.setHeader("Access-Control-Allow-Origin", "*");

                response.setContentType("application/json;charset=UTF-8");
                logger.error(exception.getMessage(),exception);
                response.getWriter().write(MAPPER.writeValueAsString(ResutlMsg.error(exception.getMessage())));
                response.getWriter().close();
        }
}
