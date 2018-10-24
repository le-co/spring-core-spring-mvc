package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class LoginFailureEventHandler implements ApplicationListener<LoginFailureEvent> {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(LoginFailureEvent event) {
        Authentication authentication = (Authentication) event.getSource();

        String userAccount = authentication.getPrincipal().toString();

        updateUserInformation(userAccount);

        System.out.println("LoginEvent Failure for : " + authentication.getPrincipal());
    }

    private void updateUserInformation(String userAccount) {
        User result = this.service.findByUserName(userAccount);

        if (result != null) {
            result.setFailAuthentication(result.getFailAuthentication() + 1);

            if (result.getFailAuthentication() > 5) {
                result.setEnabled(false);
                System.out.println("Locked account " + userAccount);
            }

            this.service.saveOrUpdate(result);
        }
    }
}
