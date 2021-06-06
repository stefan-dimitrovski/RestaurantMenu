package mk.ukim.finki.wp.stefan.restaurant.web;

import mk.ukim.finki.wp.stefan.restaurant.model.Menu;
import mk.ukim.finki.wp.stefan.restaurant.model.MenuItem;
import mk.ukim.finki.wp.stefan.restaurant.model.MenuType;
import mk.ukim.finki.wp.stefan.restaurant.service.MenuItemService;
import mk.ukim.finki.wp.stefan.restaurant.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {

    private final MenuService menuService;
    private final MenuItemService menuItemService;

    public MenuController(MenuService menuService, MenuItemService menuItemService) {
        this.menuService = menuService;
        this.menuItemService = menuItemService;

    }

    @GetMapping(value = {"/","/menu"})
    public String showMenus(@RequestParam(required = false) String nameSearch,
                            @RequestParam(required = false) MenuType menuType,
                            Model model) {
        List<Menu> menus;
        if (nameSearch == null && menuType == null) {
            menus = this.menuService.listAll();
        } else {
            menus = this.menuService.listMenuItemsByRestaurantNameAndMenuType(nameSearch,  menuType);
        }

        model.addAttribute("menus", menus);
        return "list";
    }

    @GetMapping("menu/add")
    public String showAdd(Model model) {
        List<MenuItem> menuItems = this.menuItemService.listAll();

        model.addAttribute("menuItems", menuItems);
        return "form";
    }

    @GetMapping("menu/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Menu menu = this.menuService.findById(id);
        List<MenuItem> menuItems = this.menuItemService.listAll();

        model.addAttribute("menu", menu);
        model.addAttribute("menuItems", menuItems);
        return "form";
    }

    @PostMapping("/menu")
    public String create(@RequestParam String name,
                         @RequestParam MenuType menuType,
                         @RequestParam List<Long> menuItemIds) {
        this.menuService.create(name, menuType, menuItemIds);

        return "redirect:/menu";
    }

    @PostMapping("/menu/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam MenuType menuType,
                         @RequestParam List<Long> menuItemIds) {
        this.menuService.update(id, name, menuType, menuItemIds);

        return "redirect:/menu";
    }

    @PostMapping("/menu/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.menuService.delete(id);

        return "redirect:/menu";
    }
}
