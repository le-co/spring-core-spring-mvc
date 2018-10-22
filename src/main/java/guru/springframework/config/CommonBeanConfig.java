package guru.springframework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jt on 12/14/15.
 */
@Configuration
@EnableJpaRepositories("guru.springframework.repositories")
public class CommonBeanConfig {

}
