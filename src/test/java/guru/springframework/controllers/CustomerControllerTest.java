package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService service;

    @InjectMocks
    private CustomerController customerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomerFullPath() throws Exception {

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(service.listAll()).thenReturn((List) customers);

        this.mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testListCustomerSimplifyPath() throws Exception {

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(service.listAll()).thenReturn((List) customers);

        this.mockMvc.perform(get("/customer/"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShowCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("test");

        when(service.getById(1)).thenReturn(customer);

        this.mockMvc.perform(get("/customer/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }


    @Test
    public void testOpenCreatePage() throws Exception {
        this.mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"));
    }

    @Test
    public void testOpenUpdatePage() throws Exception {
        this.mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"));
    }


    @Test
    public void testSaveCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("test");

        when(service.saveOrUpdate(Matchers.<Customer>any())).thenReturn(customer);

        this.mockMvc.perform(post("/customer")
                .param("firstName", "test")
                .param("lastName", "lec")
                .param("email", "test.lec@mail.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:customer/show/1"));

    }

    @Test
    public void testDeleteCustomer() throws Exception {

        this.mockMvc.perform(get("/customer/delete/1"))
                .andExpect(view().name("redirect:/customer/list"));
    }
}
