package com.codecademy.diningreviewapi.controllers;

import com.codecademy.diningreviewapi.models.User;
import com.codecademy.diningreviewapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username already exists");
                }
            }
        User newUser = userRepo.save(user);
        return newUser;
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updateUser = userRepo.findById(id);
        if(!updateUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
        }
        User userToUpdate = updateUser.get();

        userToUpdate.setCity(user.getCity());
        userToUpdate.setState(user.getState());
        userToUpdate.setZipCode(user.getZipCode());
        userToUpdate.setHasDairyAllergy(user.getHasDairyAllergy());
        userToUpdate.setHasEggAllergy(user.getHasEggAllergy());
        userToUpdate.setHasPeanutAllergy(user.getHasPeanutAllergy());

        return userToUpdate;
    }

    @GetMapping("/{name}")
    public User findByUserName(@RequestBody String name) {
        Optional<User> username = userRepo.findByName(name);

        if(!username.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
        }
        User existentUser = username.get();

        return existentUser;
    }
}

