//package com.example.demo.config;
//
//import com.example.demo.service.NhanVienService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private NhanVienService nhanVienService;
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers( "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
//                .anyRequest().authenticated()
//                //thay đổi đường dẫn tại loginPage để đổi giao diện đăng nhập
//                .and().csrf().disable().formLogin()
//                .loginPage("/login").loginProcessingUrl("/login")
//                .usernameParameter("email")
//                .passwordParameter("pass")
////                .and().logout()
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .permitAll();
//    }
//}
