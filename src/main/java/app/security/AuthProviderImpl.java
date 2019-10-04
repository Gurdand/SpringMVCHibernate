package app.security;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserService userService;

    public AuthProviderImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        User user = userService.getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден!");
        }

        String password = authentication.getCredentials().toString();

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Неверный пароль!");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        user.getRoles().forEach(
                (role) -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role))
        );

        //System.out.println(grantedAuthorities.toString());

        return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
