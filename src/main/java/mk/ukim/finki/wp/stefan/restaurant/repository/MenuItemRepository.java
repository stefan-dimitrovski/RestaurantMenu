package mk.ukim.finki.wp.stefan.restaurant.repository;

import mk.ukim.finki.wp.stefan.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {


}
