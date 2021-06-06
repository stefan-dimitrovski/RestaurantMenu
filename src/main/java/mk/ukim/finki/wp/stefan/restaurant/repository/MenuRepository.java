package mk.ukim.finki.wp.stefan.restaurant.repository;

import mk.ukim.finki.wp.stefan.restaurant.model.Menu;
import mk.ukim.finki.wp.stefan.restaurant.model.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByRestaurantNameLikeAndMenuType(String restaurantName, MenuType menuType);

    List<Menu> findByRestaurantNameLike(String restaurantName);

    List<Menu> findByMenuType(MenuType menuType);

}
