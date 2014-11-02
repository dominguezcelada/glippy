package com.glippy.shopping.services;

import com.glippy.domain.ShoppingListRepository;
import com.glippy.entity.ShoppingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/shopping")
public class ShoppingController {
    @Autowired
    ShoppingListRepository shoppingListRepository;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getAllShoppingLists(ModelMap model) {
        model.addAttribute("shoppingLists", shoppingListRepository.findAll());
        return "/shoppingLists";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllShoppingLists() {
        shoppingListRepository.deleteAll();
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingList(@RequestBody ShoppingList shoppingList, ModelMap model) {
        shoppingListRepository.save(shoppingList);
    }

    @RequestMapping(value = {"/{shoppingListName}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getShoppingList(@PathVariable String shoppingListName, ModelMap model) {
        model.addAttribute("shoppingList", shoppingListRepository.findByName(shoppingListName).get(0));
        return "/shoppingList";
    }

    @RequestMapping(value = "/{shoppingListName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteShoppingList(@PathVariable String shoppingListName) {
        shoppingListRepository.deleteByName(shoppingListName);
    }

    @RequestMapping(value = "/{shoppingListName}/{ItemName}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void addItemToShoppingList() {
        //TODO
    }

    @RequestMapping(value = "/{shoppingListName}/{ItemName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteItemFromShoppingList() {
        //TODO
    }
}
