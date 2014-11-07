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

    // Requets for ALL --- Shopping Lists

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getAllShoppingLists(ModelMap model) {
        model.addAttribute("shoppingLists", shoppingListRepository.findAll());
        return "/allShoppingLists";
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllShoppingLists() {
        shoppingListRepository.deleteAll();
    }


    // Requests BY USERNAME --- Shopping Lists

    @RequestMapping(value = {"/users/{username}"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingList(@PathVariable String username, @RequestBody ShoppingList shoppingList) {
        shoppingList.setUsername(username);
        shoppingListRepository.save(shoppingList);
    }

    @RequestMapping(value = {"/users/{username}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingLists(@PathVariable String username, ModelMap model) {
        model.addAttribute("shoppingLists", shoppingListRepository.findByUsername(username));
        return "/allShoppingLists";
    }

    @RequestMapping(value = {"/users/{username}/{listName}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingList(@PathVariable String username, @PathVariable String listName, ModelMap model) {
        model.addAttribute("shoppingList", shoppingListRepository.findByUsernameAndName(username, listName).get(0));
        return "/shoppingList";
    }
    @RequestMapping(value = {"/users/{username}/{listName}/{itemName}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingListItems(@PathVariable String username, @PathVariable String listName, ModelMap model) {
        model.addAttribute("shoppingItems", shoppingListRepository.findByUsernameAndName(username, listName).get(0).getItems());
        return "/allShoppingItems";
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserShoppingLists(@PathVariable String username) {
        shoppingListRepository.deleteByUsername(username);
    }

    @RequestMapping(value = "/users/{username}/{listName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserShoppingLists(@PathVariable String username,@PathVariable String listName) {
        shoppingListRepository.deleteByUsernameAndName(username, listName);
    }

}
