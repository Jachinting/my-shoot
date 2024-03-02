package com.shoot.myshootbg.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoot.myshootbg.entity.Restbean;
import com.shoot.myshootbg.entity.dto.Account;
import com.shoot.myshootbg.entity.vo.AuthUserVo;
import com.shoot.myshootbg.filter.JsonUsernamePasswordAuthenticationFilter;
import com.shoot.myshootbg.filter.JwtAuthorziefilter;
import com.shoot.myshootbg.service.AccountService;
import com.shoot.myshootbg.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Resource
    AccountService accountService;
    @Resource
    JwtAuthorziefilter jwtAuthorziefilter;
    @Resource
    JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(conf -> conf
//                        .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/api/notice/latest").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/image/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/api/auth/login")
                        .successHandler(this::onAuthenticationSuccess)
                        .failureHandler(this::onAuthenticationFailure)
                )
                .logout(conf -> conf
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess)
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorziefilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(conf -> conf
                        .authenticationEntryPoint(this::unAuthentication)
                        .accessDeniedHandler(this::onAucessDeney)
                )
                .build();
    }


//    @Bean
//    JsonUsernamePasswordAuthenticationFilter customAuthenticationFilter() throws Exception {
//        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();
//        filter.setAuthenticationSuccessHandler(this::onAuthenticationSuccess);
//        filter.setAuthenticationFailureHandler(this::onAuthenticationFailure);
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }


    public void onAucessDeney(HttpServletRequest request,
                              HttpServletResponse response,
                              AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(Restbean.forbidden(accessDeniedException.getMessage()).asJsonString());
    }

    public void unAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(Restbean.unauthorized(authException.getMessage()).asJsonString());
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println("login");
        User user = (User) authentication.getPrincipal();
        Account account = accountService.findAccountByNameOrEmail(user.getUsername());
        String token = jwtUtils.createJwt(user, account.getId(), account.getUsername());
        AuthUserVo authUserVo = new AuthUserVo();
        authUserVo.setExpire(jwtUtils.expireTime());
        authUserVo.setUsername(account.getUsername());
        authUserVo.setRole(account.getRole());
        authUserVo.setToken(token);
        authUserVo.setName(account.getName());
        response.getWriter().write(Restbean.success(authUserVo).asJsonString());
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println("login fail");
        response.getWriter().write("failure");
    }

    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if (jwtUtils.invalidateJwt(authorization)) {
            writer.write(Restbean.success().asJsonString());
        } else {
            writer.write(Restbean.failure(400, "退出登录失败").asJsonString());
        }
    }
}
