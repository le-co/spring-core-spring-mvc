package guru.springframework.services.reposervices;

import guru.springframework.domain.security.Role;
import guru.springframework.repositories.RoleRepository;
import guru.springframework.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpadao"})
public class RoleRepositoryImp implements RoleService {

    private RoleRepository repository;

    @Autowired
    public void setRepository(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        this.repository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return this.repository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }
}
