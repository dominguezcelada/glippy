package com.glippy.shopping.services;

import com.glippy.domain.ItemRepository;
import com.glippy.domain.ShoppingListRepository;
import com.glippy.entity.Item;
import com.glippy.entity.Price;
import com.glippy.entity.ShoppingList;
import com.glippy.entity.ShoppingListItem;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/shopping")
public class ShoppingController {
    @Autowired
    ShoppingListRepository shoppingListRepository;

    @Autowired
    ItemRepository itemRepository;

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

    @RequestMapping(value = {"/users/{username}/lists"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingList(@PathVariable String username, @RequestBody ShoppingList list) {
        list.setUsername(username);
        shoppingListRepository.save(list);
    }

    @RequestMapping(value = {"/users/{username}/lists"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getShoppingLists(@PathVariable String username, ModelMap model) {
        model.addAttribute("shoppingLists", shoppingListRepository.findByUsername(username));
        return "/allShoppingLists";
    }

    @RequestMapping(value = "/users/{username}/lists", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteShoppingLists(@PathVariable String username) {
        shoppingListRepository.deleteByUsername(username);
    }




    //
    // Requests BY USERNAME --- SELECTED Shopping List
    //

    @RequestMapping(value = {"/users/{username}/lists/{idList}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getShoppingList(@PathVariable String username, @PathVariable String idList, ModelMap model) {
        ShoppingList shoppingList = shoppingListRepository.findOne(idList);
        model.addAttribute("shoppingList", shoppingList);
        double total = 0;
        for (int i = 0; i < shoppingList.getListItems().size(); i++) {
            List<Price> prices = shoppingList.getListItems().get(i).getItem().getPrices();
            String selectedSupermarket = shoppingList.getListItems().get(i).getSelectedSupermarket();
            for(int j = 0; j < prices.size(); j++)
                if(prices.get(j).getSupermarket().equals(selectedSupermarket)) total += shoppingList.getListItems().get(i).getQuantity() * prices.get(j).getPrice();
        }
        model.addAttribute("total", total);
        return "/shoppingList";
    }

    @RequestMapping(value = {"/users/{username}/lists/{idList}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateShoppingList(@PathVariable String username, @PathVariable String idList, @RequestBody String newListName) {
        ShoppingList list = shoppingListRepository.findOne(idList);
        list.setName(newListName);
        shoppingListRepository.save(list);
        return "/shoppingList";
    }

    @RequestMapping(value = "/users/{username}/lists/{idList}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserShoppingList(@PathVariable String username,@PathVariable String idList) {
        shoppingListRepository.delete(idList);
    }





    //
    // Requests BY USERNAME --- SELECTED Item in Shoppping List
    //

    @RequestMapping(value = {"/users/{username}/lists/{idList}/items"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addItemUserShoppingList(@PathVariable String username, @PathVariable String idList, @RequestBody String idItem) {
        Item item = itemRepository.findOne(idItem);
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(idList));
        ShoppingListItem shoppingListItem = new ShoppingListItem(item);
        Update queryUpdate = new Update().push("listItems", shoppingListItem);
        shoppingListRepository.addItem(querySelect, queryUpdate);
    }



    @RequestMapping(value = {"/users/{username}/lists/{idList}/items/{itemId}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingListItems(@PathVariable String username, @PathVariable String idList, @PathVariable String itemId, ModelMap model) {
        List<ShoppingListItem> items = shoppingListRepository.findOne(idList).getListItems();
        ShoppingListItem item = null;
        boolean found = false;
        for(int i = 0; i < items.size() && !found; i++) {
            if(items.get(i).getItem().getId().equals(itemId)) {
                item = items.get(i);
                found = true;
            }
        }
        model.addAttribute("shoppingItem", item);
        double total = 0;
        for (int i = 0; i < item.getItem().getPrices().size(); i++) {
            if(item.getItem().getPrices().get(i).getSupermarket().equals(item.getSelectedSupermarket())) total = item.getQuantity() * item.getItem().getPrices().get(i).getPrice();
        }
        model.addAttribute("total", total);
        return "/shoppingItem";
    }

    @RequestMapping(value = {"/users/{username}/lists/{listId}/items/{itemId}/quantity"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editQuantityUserShoppingListItem(@PathVariable String username, @PathVariable String listId, @PathVariable String itemId, @RequestBody String quantity) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                .and("listItems.item._id").is(new ObjectId(itemId)));
        Update queryUpdate = new Update().set("listItems.$.quantity", Integer.valueOf(quantity));
        shoppingListRepository.updateQuantity(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/users/{username}/lists/{listId}/items/{itemId}/price"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editSelectedPriceUserShoppingListItem(@PathVariable String username, @PathVariable String listId, @PathVariable String itemId, @RequestBody String newSupermarket) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                        .and("listItems.item._id").is(new ObjectId(itemId)));
        Update queryUpdate = new Update().set("listItems.$.selectedSupermarket", newSupermarket);
        shoppingListRepository.updateQuantity(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/users/{username}/lists/{listId}/items/{itemId}/check"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editCheckStatusUserShoppingListItem(@PathVariable String username, @PathVariable String listId, @PathVariable String itemId, @RequestBody String check) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                        .and("listItems.item._id").is(new ObjectId(itemId)));
        Update queryUpdate = new Update().set("listItems.$.checked", Boolean.valueOf(check));
        shoppingListRepository.updateQuantity(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/users/{username}/lists/{listId}/items/{itemId}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeItemFromUserShoppingListItem(@PathVariable String username, @PathVariable String listId, @PathVariable String itemId) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId)));
        Update queryUpdate = new Update().pull("listItems", new Query().addCriteria(Criteria.where("item._id").is(new ObjectId(itemId))));
        shoppingListRepository.removeItem(querySelect, queryUpdate);
    }


}
