package guru.springframework.services.security;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImp implements LoginService {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void activateAccounts() {

        System.out.println("Check accounts locked");

        List<User> users = (List<User>) service.listAll();

        users.forEach(p -> {
            if (!p.getEnabled()) {
                p.setEnabled(true);

                this.service.saveOrUpdate(p);

                System.out.println("User unlocked " + p.getUsername());
            }
        });
    }
}
