package com.dotv.perfume.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests() // bat dau cau hinh security

                // cho phép các request static không bị ràng buộc

                .antMatchers("/user/**", "/manager/**", "/upload/**").permitAll()

                // các request kiểu: "/admin/" phải đăng nhập

                .antMatchers("/admin/**").hasAnyAuthority("ADMIN_S")
                .antMatchers("/manage_acc").hasAnyAuthority("ADMIN_S", "GUEST")
                .antMatchers("/update_acc").hasAnyAuthority("ADMIN_S", "GUEST")
                .antMatchers("/update_pass").hasAnyAuthority("ADMIN_S", "GUEST")
                .antMatchers("/order_acc").hasAnyAuthority("ADMIN_S", "GUEST")
                .antMatchers("/product_cart").hasAnyAuthority("ADMIN_S", "GUEST")
                /* .antMatchers("/admin/**").permitAll() */
                .and()

                // cấu hình trang đăng nhập
                .formLogin().loginPage("/login.html")//trang đăng nhập tùy chỉnh
                .loginProcessingUrl("/perform_login")//url submit username, pass
                .defaultSuccessUrl("/login_success", true)//Trang đích sau khi đăng nhập thành công
                .failureUrl("/login.html?login_error=true")//Trang đích sau khi đăng nhập thất bại
                .permitAll()

                .and()

                 //cấu hình cho phần logout
                .logout()
                .logoutUrl("/logout.html")
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();


        // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
//        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4));
    }
}
