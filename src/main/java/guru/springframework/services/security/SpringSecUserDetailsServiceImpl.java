package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

    private UserService service;

    private Converter<User, UserDetails> userUserDetailsConverter;

    @Autowired
    @Qualifier("userToUserDetails")
    public void setConvert(Converter<User, UserDetails> convert) {
        this.userUserDetailsConverter = convert;
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userUserDetailsConverter.convert(this.service.findByUserName(username));
    }
}
