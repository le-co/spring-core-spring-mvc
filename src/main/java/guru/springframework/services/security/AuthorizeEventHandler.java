package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeEventHandler implements ApplicationListener<AuthorizeEvent> {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(AuthorizeEvent event) {
        Authentication authentication = (Authentication) event.getSource();

        UserDetails userAccount = (UserDetails) authentication.getPrincipal();

        System.out.println("Logged with success");

        updateUserInformation(userAccount.getUsername());
    }


    private void updateUserInformation(String userAccount) {
        User user = this.service.findByUserName(userAccount);

        if (user != null && user.getFailAuthentication() > 0) {
            user.setEnabled(true);
            user.setFailAuthentication(0);

            this.service.saveOrUpdate(user);
        }
    }
}
