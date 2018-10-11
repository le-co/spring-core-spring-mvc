package guru.springframework.services;

import guru.springframework.config.SpringJPAConfigurationTest;
import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringJPAConfigurationTest.class)
public class ProductServiceImpTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testGetAll() {
        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() > 0;
    }

    @Test
    public void createProduct() {
        Product product = new Product("Product 5", new BigDecimal(12), "Description");

        Product result = this.productService.saveOrUpdate(product);

        assert result.getId() > 0;

        Product productFound = this.productService.getById(result.getId());

        assert product.getDescription().equals(productFound.getDescription());
    }

    @Test
    public void removeProduct() {
        Product product = new Product("Product 5", new BigDecimal(12), "Description");

        Product result = this.productService.saveOrUpdate(product);

        this.productService.delete(result.getId());

        Product productFound = this.productService.getById(result.getId());

        assert productFound == null;
    }
}
