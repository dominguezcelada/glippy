package com.glippy.shopping.services;

import com.glippy.domain.UserRepository;
import com.glippy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    // Requets for ALL --- Users

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userRepository.findAll());
        return "/allUsers";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateAllUsers(ModelMap model) {
        //TODO
        //model.addAttribute("users", userRepository.update());
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


    // Requests BY NAME --- Shopping Lists

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user, ModelMap model) {
        userRepository.save(user);
    }

    @RequestMapping(value = {"/{username}"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable String username, ModelMap model) {
        model.addAttribute("user", userRepository.findByUsername(username));
        return "/allUsers";
    }

    @RequestMapping(value = {"/{username}"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateShoppingList(@PathVariable String username, @RequestBody User user, ModelMap model) {
        //TODO

    }

    @RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteShoppingList(@PathVariable String username) {
        userRepository.deleteByUsername(username);
    }
}
