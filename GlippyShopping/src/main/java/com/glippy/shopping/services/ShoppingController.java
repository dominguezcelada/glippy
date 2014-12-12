package com.glippy.shopping.services;

import com.glippy.domain.ItemRepository;
import com.glippy.domain.ShoppingListRepository;
import com.glippy.entity.Item;
import com.glippy.entity.Price;
import com.glippy.entity.ShoppingList;
import com.glippy.entity.ShoppingListItem;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    ItemRepository itemRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    //
    // Requets for ALL --- Shopping Lists
    //

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getAllShoppingLists(ModelMap model) {
        Query querySelect = new Query()
                .with(new Sort(new Sort.Order(Sort.Direction.DESC, "createdDate")));
        model.addAttribute("shoppingLists", shoppingListRepository.findCustom(querySelect));
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

    @RequestMapping(value = {"/lists"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createShoppingList(@RequestHeader(value = "Authorization", required=false) String credentials, @RequestBody ShoppingList list) {
        list.setUsername(credentials.replace("Basic ",""));
        shoppingListRepository.save(list);
    }

    @RequestMapping(value = {"/lists"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getShoppingLists(ModelMap model, @RequestHeader(value = "Authorization", required=false) String credentials) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("username").is(credentials.replace("Basic ","")))
                .with(new Sort(new Sort.Order(Sort.Direction.DESC, "createdDate")));
        model.addAttribute("shoppingLists", shoppingListRepository.findCustom(querySelect));
//        model.addAttribute("shoppingLists", shoppingListRepository.findByUsername(credentials.replace("Basic ","")));
        return "/allShoppingLists";
    }

    @RequestMapping(value = "/lists", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteShoppingLists(@RequestHeader(value = "Authorization", required=false) String credentials) {
        shoppingListRepository.deleteByUsername(credentials.replace("Basic ",""));
    }




    //
    // Requests BY USERNAME --- SELECTED Shopping List
    //

    @RequestMapping(value = {"/lists/{idList}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getShoppingList(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String idList, ModelMap model) {
        ShoppingList shoppingList = shoppingListRepository.findOne(idList);
        model.addAttribute("shoppingList", shoppingList);
        double total = shoppingList.getTotal();
        model.addAttribute("total", total);
        return "/shoppingList";
    }

    @RequestMapping(value = {"/lists/{idList}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public String updateShoppingList(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String idList, @RequestBody String newListName) {
        ShoppingList list = shoppingListRepository.findOne(idList);
        list.setName(newListName);
        shoppingListRepository.save(list);
        return "/shoppingList";
    }

    @RequestMapping(value = {"/lists/{listId}/bestprice"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editSelectedPriceAllUserShoppingListItems(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String listId, @RequestBody String newSupermarket) {
        ShoppingList list = shoppingListRepository.findOne(listId);
        List<ShoppingListItem> items = list.getListItems();
        for(int i = 0; i < items.size(); i++) {
            Query querySelect = new Query()
                    .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                            .and("listItems.item._id").is(new ObjectId(items.get(i).getItem().getId())));
            Update queryUpdate = new Update().set("listItems.$.selectedSupermarket", newSupermarket);
            shoppingListRepository.updateShoppingList(querySelect, queryUpdate);
        }
    }

    @RequestMapping(value = "/lists/{idList}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserShoppingList(@RequestHeader(value = "Authorization", required=false) String credentials,@PathVariable String idList) {
        shoppingListRepository.delete(idList);
    }





    //
    // Requests BY USERNAME --- SELECTED Item in Shoppping List
    //

    @RequestMapping(value = {"/lists/{idList}/items"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addItemUserShoppingList(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String idList, @RequestBody String idItem) {
        Item item = itemRepository.findOne(idItem);
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(idList));
        ShoppingListItem shoppingListItem = new ShoppingListItem(item);
        Update queryUpdate = new Update().push("listItems", shoppingListItem);
        shoppingListRepository.addItem(querySelect, queryUpdate);
    }



    @RequestMapping(value = {"/lists/{idList}/items/{itemId}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserShoppingListItems(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String idList, @PathVariable String itemId, ModelMap model) {
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

    @RequestMapping(value = {"/lists/{listId}/items/{itemId}/quantity"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editQuantityUserShoppingListItem(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String listId, @PathVariable String itemId, @RequestBody String quantity) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                .and("listItems.item._id").is(new ObjectId(itemId)));
        Update queryUpdate = new Update().set("listItems.$.quantity", Integer.valueOf(quantity));
        shoppingListRepository.updateShoppingList(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/lists/{listId}/items/{itemId}/price"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editSelectedPriceUserShoppingListItem(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String listId, @PathVariable String itemId, @RequestBody String newSupermarket) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                        .and("listItems.item._id").is(new ObjectId(itemId)));
        Update queryUpdate = new Update().set("listItems.$.selectedSupermarket", newSupermarket);
        shoppingListRepository.updateShoppingList(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/lists/{listId}/items/{itemId}/check"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void editCheckStatusUserShoppingListItem(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String listId, @PathVariable String itemId, @RequestBody String check) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId))
                        .and("listItems.item._id").is(new ObjectId(itemId)));
        Update queryUpdate = new Update().set("listItems.$.checked", Boolean.valueOf(check));
        shoppingListRepository.updateShoppingList(querySelect, queryUpdate);
    }

    @RequestMapping(value = {"/lists/{listId}/items/{itemId}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void removeItemFromUserShoppingListItem(@RequestHeader(value = "Authorization", required=false) String credentials, @PathVariable String listId, @PathVariable String itemId) {
        Query querySelect = new Query()
                .addCriteria(Criteria.where("_id").is(new ObjectId(listId)));
        Update queryUpdate = new Update().pull("listItems", new Query().addCriteria(Criteria.where("item._id").is(new ObjectId(itemId))));
        shoppingListRepository.removeItem(querySelect, queryUpdate);
    }

    /* Stats */

    @RequestMapping(value = {"/stats"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUserGeneralStats (@RequestHeader(value = "Authorization", required=false) String credentials, ModelMap model) {
        List<ShoppingList> lists = shoppingListRepository.findByUsername(credentials.replace("Basic ",""));
        double totals[] = null;

        for(int i = 0; i < lists.size(); i++) {
            totals[i] = lists.get(i).getCheckedTotal();
        }

        model.addAttribute("shoppingLists", lists);
        model.addAttribute("totals", totals);

        return "/allStats";
    }



}
