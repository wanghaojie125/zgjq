package com.zhangongjx.auth;

import com.zhangongjx.service.impl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthSuccess authSuccess;

    @Autowired
    private AuthFail authFail;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/content/**","/api/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin() //表单登录
                .loginPage("/login") //用户未登录时的处理地址
                .loginProcessingUrl("/auth/login") //用户登录
                .successHandler(authSuccess) //登录成功处理
                .failureHandler(authFail) //登录失败处理
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/login") //不拦截的URL
                .permitAll()
                .anyRequest()
                .authenticated();
        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter,CsrfFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(systemUserService()).passwordEncoder(passwordEncoder());
    }

  /**
    * 设置用户密码的加密方式为MD5加密
   */
   @Bean
   public Base64PasswordEncoder passwordEncoder() {
      return new Base64PasswordEncoder();
  }

    /**
     *从数据库中读取用户信息
     */
    @Bean
    public UserDetailsService systemUserService() {
        return new AuthService();
    }
}
