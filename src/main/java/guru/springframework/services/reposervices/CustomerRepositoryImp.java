package guru.springframework.services.reposervices;

import guru.springframework.commands.CustomerForm;
import guru.springframework.converters.CustomerFormToCustomer;
import guru.springframework.domain.Customer;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpadao"})
public class CustomerRepositoryImp implements CustomerService {

    private CustomerRepository repository;

    private CustomerFormToCustomer converter;

    @Autowired
    public void setConverter(CustomerFormToCustomer converter) {
        this.converter = converter;
    }

    @Autowired
    public void setRepository(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<?> listAll() {
        List<Customer> customers = new ArrayList<>();
        this.repository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        return this.repository.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return this.repository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        this.repository.delete(id);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer customer = this.converter.convert(customerForm);

        if (customer.getUser().getId() != null) {
            Customer existingCustomer = this.getById(customer.getId());

            customer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }


        return this.repository.save(customer);
    }
}
