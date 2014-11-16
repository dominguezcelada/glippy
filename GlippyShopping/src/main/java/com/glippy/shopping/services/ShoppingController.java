package com.glippy.shopping.services;

import com.glippy.domain.ShoppingListRepository;
import com.glippy.entity.ShoppingList;
import com.glippy.entity.ShoppingListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/shopping")
public class ShoppingController {
    @Autowired
    ShoppingListRepository shoppingListRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    //
    // Requets for ALL --- Shopping Lists
    //

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



    //
    // Requests BY USERNAME --- Shopping Lists
    //

    @RequestMapping(value = {"/users/{username}"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingList(@PathVariable String username, @RequestBody ShoppingList list) {
        list.setUsername(username);
        shoppingListRepository.save(list);
    }

    @RequestMapping(value = {"/users/{username}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getAllUserShoppingLists(@PathVariable String username, ModelMap model) {
        model.addAttribute("shoppingLists", shoppingListRepository.findByUsername(username));
        return "/allShoppingLists";
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUserShoppingLists(@PathVariable String username) {
        shoppingListRepository.deleteByUsername(username);
    }




    //
    // Requests BY USERNAME --- SELECTED Shopping List
    //

    @RequestMapping(value = {"/users/{username}/{listName}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingList(@PathVariable String username, @PathVariable String listName, ModelMap model) {
        model.addAttribute("shoppingList", shoppingListRepository.findByUsernameAndName(username, listName).get(0));
        return "/shoppingList";
    }

    @RequestMapping(value = {"/users/{username}/{listName}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateUserShoppingListName(@PathVariable String username, @PathVariable String listName, @RequestBody String newListName) {
        List<ShoppingList> list = shoppingListRepository.findByUsernameAndName(username, listName);
        if(list.size() > 0) {
            list.get(0).setName(newListName);
            shoppingListRepository.save(list);
        }
        return "/shoppingList";
    }

    @RequestMapping(value = "/users/{username}/{listName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserShoppingList(@PathVariable String username,@PathVariable String listName) {
        shoppingListRepository.deleteByUsernameAndName(username, listName);
    }




    //
    // Requests BY USERNAME --- SELECTED Item in Shoppping List
    //

    @RequestMapping(value = {"/users/{username}/{listName}/{itemName}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingListItems(@PathVariable String username, @PathVariable String listName, @PathVariable String itemName, ModelMap model) {
        List<ShoppingListItem> items = shoppingListRepository.findByUsernameAndName(username, listName).get(0).getListItems();
        ShoppingListItem item = null;
        boolean found = false;
        for(int i = 0; i < items.size() && !found; i++) {
            if(items.get(i).getItem().getName().equals(itemName)) {
                item = items.get(i);
                found = true;
            }
        }
        model.addAttribute("shoppingItem", item);
        int total = 0;
        for (int i = 0; i < item.getItem().getPrices().size(); i++) {
            if(item.getItem().getPrices().get(i).getSupermarket().equals(item.getSelectedSupermarket())) total = (int) (item.getQuantity() * item.getItem().getPrices().get(i).getPrice());
        }
        model.addAttribute("total", total);
        return "/shoppingItem";
    }

    @RequestMapping(value = {"/users/{username}/{listName}/{itemName}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editQuantityUserShoppingListItem(@PathVariable String username, @PathVariable String listName, @PathVariable String itemName, @RequestBody String quantity) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("name").is(listName)
                .and("username").is(username)
                .and("listItems.item.name").is(itemName));
        Update queryUpdate = new Update().set("listItems.$.quantity", Integer.valueOf(quantity));
        shoppingListRepository.updateQuantity(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/users/{username}/{listName}/{itemName}/price"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editSelectedPriceUserShoppingListItem(@PathVariable String username, @PathVariable String listName, @PathVariable String itemName, @RequestBody String newSupermarket) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("name").is(listName)
                        .and("username").is(username)
                        .and("listItems.item.name").is(itemName));
        Update queryUpdate = new Update().set("listItems.$.selectedSupermarket", newSupermarket);
        shoppingListRepository.updateQuantity(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/users/{username}/{listName}/{itemName}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeItemFromUserShoppingListItem(@PathVariable String username, @PathVariable String listName, @PathVariable String itemName) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("name").is(listName)
                        .and("username").is(username)
                        .and("listItems.item.name").is(itemName));
        Update queryUpdate = new Update().pull("listItems", new Query().addCriteria(Criteria.where("item.name").is(itemName)));
        shoppingListRepository.updateQuantity(querySelect, queryUpdate);
    }


}
