package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "app")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(AuthenticationSuccessHandler successHandler,
                          @Qualifier(value = "authProviderImpl") AuthenticationProvider authenticationProvider) {
        this.successHandler = successHandler;
        this.authenticationProvider = authenticationProvider;
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
                    .successHandler(successHandler)
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
        auth.authenticationProvider(authenticationProvider);
    }
}
