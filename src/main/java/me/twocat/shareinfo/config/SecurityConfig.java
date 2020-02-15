package me.twocat.shareinfo.config;


import me.twocat.shareinfo.security.JwtAuthenticationEntryPoint;
import me.twocat.shareinfo.security.JwtAuthenticationFilter;

import me.twocat.shareinfo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/***
 @author echo
 @create 2019年09月20日 9:26
 @EnableWebSecurity
 This is the primary spring security annotation that is used to enable web security in a project.
 @EnableGlobalMethodSecurity
 This is used to enable method level security based on annotations.
 You can use following three types of annotations for securing your methods -securedEnabled: It enables the @Secured annotation using which you can protect your controller/service methods like so -
 jsr250Enabled: It enables the @RolesAllowed annotation that can be used like this -
 prePostEnabled: It enables more complex expression based access control syntax with @PreAuthorize and @PostAuthorize annotations -
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
             .cors()
                 .and()
             .csrf()
                  .disable()
             .exceptionHandling()
              .authenticationEntryPoint(unauthorizedHandler)
                  .and()
             .sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
             .authorizeRequests()
                 .antMatchers(
                     "/",
                     "/favicon.ico",
                     "/**/*.png",
                     "/**/*.gif",
                     "/**/*.svg",
                     "/**/*.jpg",
                     "/**/*.html",
                     "/**/*.css",
                     "/**/*.js")
             .permitAll()
                  .antMatchers("/api/auth/**","/2catme/**")
             .permitAll()
                 .anyRequest()
                 .authenticated()
             .and()
             .exceptionHandling()
             .authenticationEntryPoint(myAuthenticationEntryPoint());
         //add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter() , UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    public AuthenticationEntryPoint myAuthenticationEntryPoint(){
        return new JwtAuthenticationEntryPoint();
    }


}
