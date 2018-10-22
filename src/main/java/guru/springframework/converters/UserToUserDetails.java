package guru.springframework.converters;

import guru.springframework.domain.User;
import guru.springframework.services.security.UserDetailsImp;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserToUserDetails implements Converter<User, UserDetails>{

    @Override
    public UserDetails convert(User source) {
        UserDetailsImp userDetailImp = new UserDetailsImp();

        userDetailImp.setUserName(source.getUsername());
        userDetailImp.setPassword(source.getPassword());
        userDetailImp.setEnabled(source.getEnabled());

        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();

        source.getRoles().forEach(p -> {
            roles.add(new SimpleGrantedAuthority(p.getRole()));
        });

        userDetailImp.setAuthorities(roles);

        return userDetailImp;
    }
}
