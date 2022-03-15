package com.codecademy.diningreviewapi.controllers;

import com.codecademy.diningreviewapi.models.User;
import com.codecademy.diningreviewapi.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    public final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/")
    public User create(@RequestBody User user) {
        List<User> userList = (List<User>) userRepo.findAll();
            for (int i=0; i<userList.size(); i++) {
                Boolean condition = user.getName().equals(userList.get(i).getName());
                if(condition){
                    return null;
                }
            }
        User newUser = userRepo.save(user);
        return newUser;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updateUser = userRepo.findById(id);
        if(!updateUser.isPresent()){
            return null;
        }
        User userToUpdate = updateUser.get();

        userToUpdate.setCity(user.getCity());
        userToUpdate.setState(user.getState());
        userToUpdate.setZipCode(user.getZipCode());
        userToUpdate.setInterestedInDairyAllergy(user.getInterestedInDairyAllergy());
        userToUpdate.setInterestedInEggAllergy(user.getInterestedInEggAllergy());
        userToUpdate.setInterestedInPeanutAllergy(user.getInterestedInPeanutAllergy());

        return userToUpdate;
    }

    @GetMapping("/{name}")
    public User findByUserName(@RequestBody String name) {
        Optional<User> username = userRepo.findByName(name);

        if(!username.isPresent()){
            return null;
        }
        User existentUser = username.get();

        return existentUser;
    }
}

