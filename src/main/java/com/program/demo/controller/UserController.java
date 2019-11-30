package com.program.demo.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.program.demo.model.User;
import com.program.demo.model.User.Role;
import com.program.demo.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for the User entity.
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired private UserRepository userRepository;


	@RequestMapping("/user")
	public Map<String,String> user(OAuth2Authentication auth2Authentication) {
    Map<String,String> details = (Map<String,String>) auth2Authentication.getUserAuthentication().getDetails();
    ;
    Set<String> keys = details.keySet();
    Object[] keyArray = keys.toArray();
    String id =  details.get(keyArray[0]);
    String username= details.get(keyArray[1]);
    String email = details.get(keyArray[2]);
    Role role = Role.ROLE_GUEST;
    System.out.println(id);
    System.out.println(username);
    System.out.println(email);
    Optional optionaluser = userRepository.findByUsername(username);
    if(optionaluser.isPresent()){
    }else{
      System.out.println("user is not present");
      User newUser = new User();
      newUser.setUsername(username);
      newUser.setClientID(id);
      newUser.setEmail(email);
      newUser.setRole(role);
      register(newUser);
      
    }

    return details;
	}


  /**
   * Returns all the registerd users.
   * @return ResponseEntity
   */
  @GetMapping("")
  public ResponseEntity<List<User>> getUsers() {
    List<User> users = userRepository.findAll();
    return ResponseEntity.ok(users);
  }

  /**
   * Returns user with the provided id if exists.
   * @param id The id of the user.
   * @return ResponseEntity
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable Integer id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      return ResponseEntity.ok(optionalUser.get());
    }
    return ResponseEntity.notFound().build();
  }
  /**
   * Registers the given user.
   * @param user The user that will be registerd.
   * @return ResponseEntity
   */
  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody User user) {
    User newUser;
    System.out.println("USERNAME"+ user.getEmail());
    Optional<User> optionalAppUser = userRepository.findByUsername(user.getUsername());
    if (optionalAppUser.isPresent()) {
      return ResponseEntity.badRequest().build();
    }
    newUser=userRepository.saveAndFlush(user);
    System.out.println("user saved");
    return  ResponseEntity.ok(newUser);
  }
  /**
   * Logs in the user.
   * @return ResponseEntity
   */
  /*@PostMapping("login")
  public ResponseEntity<User> login() {
    return ResponseEntity.ok(authenticatedUser.getUser());
  }*/

  @PutMapping("/{id}")
  public ResponseEntity<User> addRate(
    @RequestBody User user,
    @PathVariable Integer id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      User oldUser = optionalUser.get();
      user.setId(oldUser.getId());
      user.setUsername(oldUser.getUsername());
      user.setClientID(oldUser.getClientID());
      user.setEmail(oldUser.getEmail());
      user.setRole(oldUser.getRole());
      user.setRatingNumber(oldUser.getRatingNumber()+1);
      user.setRatingValue(oldUser.getRatingValue()+ user.getRatingValue());
      user.setCars(oldUser.getCars());
      user.setMotobikes(oldUser.getMotobikes());
      user.setRent(oldUser.getRent());
      user.setFavourites(oldUser.getFavourites());
      user.setReceipt(oldUser.getReceipt());
      return ResponseEntity.ok(userRepository.saveAndFlush(user));
    }
    return ResponseEntity.notFound().build();
  }

/**
 * Deletes a user.
 * @param id Id of the user.
 * @return ResponseEntity
 */

  @DeleteMapping("/{id}")
  @Secured({"ROLE_ADMIN"})
  public ResponseEntity deleteUser(@PathVariable Integer id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      userRepository.delete(optionalUser.get());
      return ResponseEntity.ok().build();
    }
    
    return ResponseEntity.notFound().build();
  }


  
}
