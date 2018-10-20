package guru.springframework.services.reposervices;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpadao"})
public class ProductRepositoryImp implements ProductService {

    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        this.repository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return this.repository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }
}
