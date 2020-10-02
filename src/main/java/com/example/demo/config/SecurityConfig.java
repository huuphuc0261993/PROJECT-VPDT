package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

//package com.example.demo.config;
//////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.boot.SpringApplication;
//////import org.springframework.boot.autoconfigure.SpringBootApplication;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.builders.WebSecurity;
//////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//////
//////import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class SecurityConfig implements WebMvcConfigurer {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//////@SpringBootApplication
//////@Configuration
//////public class SecurityConfig extends WebSecurityConfigurerAdapter {
////////
////////
////////    @Override
////////    protected void configure(HttpSecurity http) throws Exception {
////////        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
////////    }
////////
////////    @Override
////////    public void configure(WebSecurity web) throws Exception {
////////        web.ignoring().antMatchers("/css/**","/js/**","/img/**");
////////    }
////////    @Override
////////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////////
////////        auth.inMemoryAuthentication()
////////                .withUser("admin")
////////                .password("{noop}password")
////////                .roles("USER");
////////
////////    }
//////@Autowired
//////private DataSource dataSource;
//////    @Bean
//////    public BCryptPasswordEncoder encoder(){
//////        return new BCryptPasswordEncoder();
//////    }
//////    @Autowired
//////    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//////        auth.jdbcAuthentication().dataSource(dataSource)
//////                .usersByUsernameQuery("SELECT email, pass, enabled FROM nhan_viens WHERE email = ?;")
//////                .authoritiesByUsernameQuery("SELECT email ,concat('ROLE_',roles) as roles FROM nhan_viens WHERE email = ?;")
//////                .passwordEncoder(encoder());
//////    }
//////    @Override
//////    protected void configure(HttpSecurity http) throws Exception {
//////        //khong can dang nhap van vao duoc
//////        http.authorizeRequests()
//////                .antMatchers(
////////                        "/", "/category/*","/all","/search",
////////                        "/categoryRest/**","/donateRest/**", "/volunteerRest/**",
////////                        "/VoteImageRest/**", "/voteRest/**", "/create_account/login",
////////                        "/create_account/create_account"
//////                )
//////                .permitAll();
//////        //dang nhap moi vao xem duoc
//////        http.authorizeRequests()
//////                .antMatchers( "/user/**")
//////                .authenticated();
//////        //Chi user moi vao duoc
//////        http.authorizeRequests()
//////                .antMatchers("/user/**")
//////                .access("hasAnyRole('USER','ADMIN')");
//////        //tat ca cac duong dan co quyen admin moi xem duoc
//////        http.authorizeRequests()
//////                .antMatchers("/admin/**")
//////                .access("hasRole('ADMIN')");
//////        //Xu ly khi nguoi dung vao trang khong co quyen de vao
//////        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/");
//////        http.authorizeRequests()
//////                .and().formLogin()
//////                .loginPage("/login")
//////                .loginProcessingUrl("/loginAccount").permitAll()
//////                .defaultSuccessUrl("/")
//////                .failureUrl("/create-account")
//////                .usernameParameter("username")
//////                .passwordParameter("password")
//////                .and().logout()
//////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//////                .logoutSuccessUrl("/");
//////        http.csrf().disable();
//////    }
//////
//////}
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM nhan_viens WHERE username = ?;")
                .authoritiesByUsernameQuery("SELECT username ,concat('ROLE_',roles) as roles FROM nhan_viens WHERE username = ?;")
                .passwordEncoder(encoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //khong can dang nhap van vao duoc
        http.authorizeRequests()
                .antMatchers("/css/**","/js/**","/img/**")
                .permitAll();
        //dang nhap moi vao xem duoc
        http.authorizeRequests()
                .antMatchers("/admin/**", "/user/**")
                .authenticated();
        //Chi user moi vao duoc
        http.authorizeRequests()
                .antMatchers("/user/**")
                .access("hasAnyRole('USER','ADMIN')");
        //tat ca cac duong dan co quyen admin moi xem duoc
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasRole('ADMIN')");
        //Xu ly khi nguoi dung vao trang khong co quyen de vao
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/");
        http.authorizeRequests()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login").permitAll()
                .defaultSuccessUrl("/thongbao/default")
                .failureUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
        http.csrf().disable();
    }
}