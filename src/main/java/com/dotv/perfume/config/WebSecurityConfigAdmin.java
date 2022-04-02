package com.dotv.perfume.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class WebSecurityConfigAdmin extends WebSecurityConfigurerAdapter {


    @Qualifier("adminDetailServiceImpl")
    @Autowired
    private UserDetailsService adminDetailsServiceImpl;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(passwordEncoder());
//    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/manage/**","/uploads/**","/user/**","/utils/**");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                //.antMatchers("/manage/**","/uploads/**","/user/**","/utils/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN_MB","ADMIN_MP","ADMIN_MN","ADMIN_MI","ADMIN_MO","ADMIN_ME","ADMIN_MC","ADMIN_MR","ADMIN_MU")
                //.anyRequest().authenticated()
                .and()

                // cấu hình trang đăng nhập
                .formLogin().loginPage("/admin/login_admin.html")//trang đăng nhập tùy chỉnh
                .loginProcessingUrl("/admin/perform_login1")//url submit username, pass
                .defaultSuccessUrl("/admin/home", true)//Trang đích sau khi đăng nhập thành công
                .failureUrl("/admin/login_admin.html?login_error=true")//Trang đích sau khi đăng nhập thất bại
                .permitAll()

                .and()

                //cấu hình cho phần logout
                .logout()
                .logoutUrl("/admin/logout_admin.html")
                .logoutSuccessUrl("/admin/check_login_admin")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();


        // Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }
}
