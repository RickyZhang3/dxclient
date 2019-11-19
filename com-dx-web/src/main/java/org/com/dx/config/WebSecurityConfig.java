package org.com.dx.config;

import org.com.dx.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//    private Environment env;
	
	@Autowired
	LoginServiceImpl LoginServiceImpl;
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth.userDetailsService(LoginServiceImpl).passwordEncoder(passwordEncoder());
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        	http.authorizeRequests().antMatchers("/api","/v2/**","/swagger-ui.html","/webjars/**","/swagger-resources/**","/dxlogin/nologin","/yxTag/getAudioListJsonp","/yxTag/getAudioList","/dx/getAudio")
        	.permitAll()
        	.anyRequest().authenticated()
        	.and()
        	.csrf().disable()
		    .headers().frameOptions().disable()
		    .and()
        	.formLogin()
        	.failureForwardUrl("/dxlogin/nologin")
        	.successForwardUrl("/dxlogin/success")
        	.permitAll();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//	@Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//             User.withUsername(env.getProperty("spring.security.user.username"))
//                .password(env.getProperty("spring.security.user.password"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
