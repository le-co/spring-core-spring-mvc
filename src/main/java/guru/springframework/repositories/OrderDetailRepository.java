package guru.springframework.repositories;

import guru.springframework.domain.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
}
