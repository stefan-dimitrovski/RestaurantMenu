package mk.ukim.finki.wp.stefan.restaurant.service.impl;

import mk.ukim.finki.wp.stefan.restaurant.model.MenuItem;
import mk.ukim.finki.wp.stefan.restaurant.model.exceptions.InvalidMenuIdException;
import mk.ukim.finki.wp.stefan.restaurant.repository.MenuItemRepository;
import mk.ukim.finki.wp.stefan.restaurant.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem findById(Long id) {
        return this.menuItemRepository.findById(id).orElseThrow(InvalidMenuIdException::new);
    }

    @Override
    public List<MenuItem> listAll() {
        return this.menuItemRepository.findAll();
    }

    @Override
    public MenuItem create(String name, String description) {
        MenuItem menuItem = new MenuItem(name, description);
        return this.menuItemRepository.save(menuItem);
    }
}
