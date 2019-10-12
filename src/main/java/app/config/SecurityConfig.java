package app.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "app")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(AuthenticationSuccessHandler successHandler,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.authenticationSuccessHandler = successHandler;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/").permitAll()
                .antMatchers("/admin", "/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/")
                    .usernameParameter("login")
                    .loginProcessingUrl("/login")
                    .permitAll()
                    .successHandler(authenticationSuccessHandler)
                .and()
                    .exceptionHandling().accessDeniedPage("/")
                .and()
                    .logout().permitAll()
                .and()
                    .csrf().disable()
                .sessionManagement()
                    .maximumSessions(1).expiredUrl("/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}
