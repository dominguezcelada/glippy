package com.glippy.shopping.services;

import com.glippy.domain.ShoppingListRepository;
import com.glippy.entity.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oscar on 02/11/2014.
 */

@Controller
@RequestMapping(value = "/shopping")
public class ShoppingController {
    @Autowired
    ShoppingListRepository shoppingListRepository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String getShoppingLists(ModelMap model) {
        model.addAttribute("shoppingLists", shoppingListRepository.findAll());
        return "/shoppingLists";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteShoppingList() {
        shoppingListRepository.deleteAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingLists(@RequestBody ShoppingList shoppingList, ModelMap model) {
        shoppingListRepository.save(shoppingList);
    }

    @RequestMapping(value = {"/{shoppingListName}"}, method = RequestMethod.GET)
    public String getShoppingList(@PathVariable String shoppingListName, ModelMap model) {
        model.addAttribute("shoppingList", shoppingListRepository.findByName(shoppingListName).get(0));
        return "/shoppingList";
    }


}
