package guru.springframework.repositories;

import guru.springframework.domain.CartDetail;
import org.springframework.data.repository.CrudRepository;

public interface CartDetailRepository extends CrudRepository<CartDetail, Integer> {
}
