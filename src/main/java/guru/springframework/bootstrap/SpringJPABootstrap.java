package guru.springframework.bootstrap;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadProducts();
    }

    private void loadProducts() {
        Product product = new Product("Product 1", new BigDecimal(10), "https://product1.com.br");
        this.productService.saveOrUpdate(product);

        Product product1 = new Product("Product 2", new BigDecimal(10), "https://product2.com.br");
        this.productService.saveOrUpdate(product1);

        Product product2 = new Product("Product 3", new BigDecimal(10), "https://product3.com.br");
        this.productService.saveOrUpdate(product2);

        Product product3 = new Product("Product 4", new BigDecimal(10), "https://product4.com.br");
        this.productService.saveOrUpdate(product3);
    }
}
