package mk.ukim.finki.wp.stefan.restaurant.service.impl;

import mk.ukim.finki.wp.stefan.restaurant.model.Menu;
import mk.ukim.finki.wp.stefan.restaurant.model.MenuItem;
import mk.ukim.finki.wp.stefan.restaurant.model.MenuType;
import mk.ukim.finki.wp.stefan.restaurant.model.exceptions.InvalidMenuIdException;
import mk.ukim.finki.wp.stefan.restaurant.repository.MenuItemRepository;
import mk.ukim.finki.wp.stefan.restaurant.repository.MenuRepository;
import mk.ukim.finki.wp.stefan.restaurant.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuItemRepository menuItemRepository;

    public MenuServiceImpl(MenuRepository menuRepository, MenuItemRepository menuItemRepository) {
        this.menuRepository = menuRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<Menu> listAll() {
        return this.menuRepository.findAll();
    }

    @Override
    public Menu findById(Long id) {
        return this.menuRepository.findById(id).orElseThrow(InvalidMenuIdException::new);
    }

    @Override
    public Menu create(String restaurantName, MenuType menuType, List<Long> menuItemsIds) {
        List<MenuItem> menuItems = this.menuItemRepository.findAllById(menuItemsIds);
        Menu menu = new Menu(restaurantName, menuType, menuItems);

        return this.menuRepository.save(menu);
    }

    @Override
    public Menu update(Long id, String restaurantName, MenuType menuType, List<Long> menuItemsIds) {
        Menu menu = this.findById(id);
        List<MenuItem> menuItems = this.menuItemRepository.findAllById(menuItemsIds);
        menu.setRestaurantName(restaurantName);
        menu.setMenuType(menuType);
        menu.setMenuItems(menuItems);

        return this.menuRepository.save(menu);
    }

    @Override
    public Menu delete(Long id) {
        Menu menu = this.findById(id);
        this.menuRepository.delete(menu);
        return menu;
    }

    @Override
    public List<Menu> listMenuItemsByRestaurantNameAndMenuType(String restaurantName, MenuType menuType) {

        String nameLike = "%"+ restaurantName + "%";
        if(restaurantName != null && menuType != null){
            return this.menuRepository.findByRestaurantNameLikeAndMenuType(nameLike, menuType);
        }else if(restaurantName != null){
            return this.menuRepository.findByRestaurantNameLike(nameLike);
        }else if(menuType != null){
            return this.menuRepository.findByMenuType(menuType);
        }else{
            return this.menuRepository.findAll();
        }
    }
}
