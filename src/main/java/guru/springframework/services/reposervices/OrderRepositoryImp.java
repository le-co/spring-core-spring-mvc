package guru.springframework.services.reposervices;

import guru.springframework.domain.Order;
import guru.springframework.domain.Product;
import guru.springframework.repositories.OrderRepository;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.OrderService;
import guru.springframework.services.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpadao"})
public class OrderRepositoryImp implements OrderService {

    private OrderRepository repository;

    @Autowired
    public void setRepository(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> listAll() {
        List<Order> orders = new ArrayList<>();
        this.repository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order getById(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public Order saveOrUpdate(Order domainObject) {
        return this.repository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }
}
