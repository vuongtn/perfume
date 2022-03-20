//package com.dotv.perfume.config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@Order(1)
//public class WebSecurityConfigAdmin extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private UserDetailsService adminDetailServiceImpl;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(adminDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder(4));
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests() // bat dau cau hinh security
//
//                // cho phép các request static không bị ràng buộc
//
////                .antMatchers("/user/**", "/manager/**", "/upload/**").permitAll()
//
//                // các request kiểu: "/admin/" phải đăng nhập
////                .antMatchers("/login.html/**").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN_S")
//                .and()
//
//                // cấu hình trang đăng nhập
//
//                .formLogin().loginPage("/login_admin.html")//trang đăng nhập tùy chỉnh
//                .loginProcessingUrl("/perform_login1")//url submit username, pass
//                .defaultSuccessUrl("/admin/login_success", true)//Trang đích sau khi đăng nhập thành công
//                .failureUrl("/login_admin.html?login_error=true")//Trang đích sau khi đăng nhập thất bại
//                .permitAll()
//
//                .and()
//
//                //cấu hình cho phần logout
//                .logout()
//                .logoutUrl("/logout_admin.html")
//                .logoutSuccessUrl("/login_admin.html")
//                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
//
//
//
//        // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
////        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(adminDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
////    }
//}
