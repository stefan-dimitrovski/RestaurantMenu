package mk.ukim.finki.wp.stefan.restaurant.service;

import mk.ukim.finki.wp.stefan.restaurant.model.MenuItem;

import java.util.List;

public interface MenuItemService {

    MenuItem findById(Long id);

    List<MenuItem> listAll();

    MenuItem create(String name, String description);

}
