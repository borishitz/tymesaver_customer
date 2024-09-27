//package com.tracker.recordSearch.secure;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//@Configuration
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/","/registration/**","/login","/payhome","/package","/payment/create","/membership/payment/create","/payment/success","/payment/cancel","/payment/error",
//                        "/dashboard", "/about","/ourServices","/community","/personality","/cognition","/team","/css/**","/img/**","/js/**",
//                        "/dashboardAssets/**","/contact", "/contact/save",
//                        "/registration-form","/user/register").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .defaultSuccessUrl("/dashboard", true)
//                .and()
//                .logout().invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").permitAll();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    //    the bean to carry out authentication
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        return provider;
//    }
//}
