package guru.springframework.services.reposervices;

import guru.springframework.domain.User;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpadao"})
public class UserRepositoryImp implements UserService {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        this.repository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getById(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return this.repository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }
}
