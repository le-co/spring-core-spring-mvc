package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Profile("jpa")
@Service
public class CustomerServiceJpaImpl implements CustomerService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<?> listAll() {

        EntityManager em = emf.createEntityManager();

        List<Customer> customers = em.createQuery("from Customer", Customer.class).getResultList();

        return customers;
    }

    @Override
    public Customer getById(Integer id) {

        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, id);

        return customer;
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Customer customer = em.merge(domainObject);

        em.getTransaction().commit();

        return customer;
    }

    @Override
    public void delete(Integer id) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.remove(this.getById(id));

        em.getTransaction().commit();

    }
}
