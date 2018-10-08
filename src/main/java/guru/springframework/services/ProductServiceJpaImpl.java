package guru.springframework.services;

import guru.springframework.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Profile("jpa")
@Service
public class ProductServiceJpaImpl implements ProductService {

    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<?> listAll() {

        EntityManager em = emf.createEntityManager();

        List<Product> products = em.createQuery("from Product", Product.class).getResultList();

        return products;
    }

    @Override
    public Product getById(Integer id) {

        EntityManager em = emf.createEntityManager();

        Product product = em.find(Product.class, id);

        return product;
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Product product = em.merge(domainObject);

        em.getTransaction().commit();

        return product;
    }

    @Override
    public void delete(Integer id) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        em.remove(this.getById(id));

        em.getTransaction().commit();

    }
}
