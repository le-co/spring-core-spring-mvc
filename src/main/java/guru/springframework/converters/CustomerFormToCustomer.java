package guru.springframework.converters;

import guru.springframework.commands.CustomerForm;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerFormToCustomer implements Converter<CustomerForm, Customer> {

    @Override
    public Customer convert(CustomerForm customerForm) {
        Customer customer = new Customer();

        customer.setId(customerForm.getCustomerId());
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        customer.setEmail(customerForm.getEmail());
        customer.setPhoneNumber(customerForm.getPhoneNumber());
        customer.setVersion(customerForm.getCustomerVersion());
        customer.setUser(new User());
        customer.getUser().setUsername(customerForm.getUserName());
        customer.getUser().setPassword(customerForm.getPasswordText());
        customer.getUser().setVersion(customerForm.getUserVersion());
        customer.getUser().setId(customerForm.getUserId());

        return customer;
    }
}
