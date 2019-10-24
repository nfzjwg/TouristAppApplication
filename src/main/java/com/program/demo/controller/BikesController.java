package com.program.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import com.program.demo.model.Motobikes;
import com.program.demo.model.User;
import com.program.demo.repositories.BikesRepository;
import com.program.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/motobikes")
@CrossOrigin
@RestController
public class BikesController{

    @Autowired private BikesRepository bikeRepository;
    @Autowired private UserRepository userRepository;
    /**
     * This function returns all the motorcycles in the table.
     * @return ResponseEntity
     */
    @GetMapping("")
    public ResponseEntity<List<Motobikes>> getBikes() {
        List<Motobikes> cars = bikeRepository.findAll();
        return ResponseEntity.ok(cars);
    }
    /**
     * Returns the specified motorcycle with the matching id.
     * @param id The id of the motorcycle.
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Motobikes> getBike(@PathVariable Integer id) {
        Optional<Motobikes> optionalUser = bikeRepository.findById(id);
        if (optionalUser.isPresent()) {
          return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.notFound().build();
    }
    /**
     * Deletes te motorcycle with the matching id.
     * @param id The id of the motorcycle.
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Motobikes> deleteBike(@PathVariable Integer id) {
      Optional<Motobikes> optionalComment = bikeRepository.findById(id);
      if (optionalComment.isPresent()) {
        bikeRepository.delete(optionalComment.get());
        return ResponseEntity.ok().build();
      }
      return ResponseEntity.notFound().build();
    }

/**
 * Returns all the motobikes that are the given owner's motobikes.
 * @param owner The id of the owner.  
 * @return ResponsEntry
 */
@GetMapping("/by-user")
  
public ResponseEntity<List<Motobikes>> getCarsByUser(@PathParam(value = "owner") Integer owner) {
  List<Motobikes> cars = bikeRepository.findAllByOwnerId(owner);
  return ResponseEntity.ok(cars);
}


    /**
     * Adds a motorcycle to the table.
     * @param bike This is the motorcycle.
     * @param owner The id of the owner.
     * @return ResponseEntity
     */
    @PostMapping("/upload")
    @Secured({"ROLE_ADMIN", "ROLE_COMPANY"})
    public ResponseEntity<Motobikes> addBike(
        @RequestBody Motobikes bike, @PathParam(value = "owner") Integer owner) {
        Optional<User> optionalUser = userRepository.findById(owner);
        if (optionalUser.isPresent()) {
        bike.setOwner(optionalUser.get());
        Motobikes newBike = bikeRepository.saveAndFlush(bike);
        return ResponseEntity.ok(newBike);
        }
        return ResponseEntity.notFound().build();
    }

  
}
