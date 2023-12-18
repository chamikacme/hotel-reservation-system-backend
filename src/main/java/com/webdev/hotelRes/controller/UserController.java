package com.webdev.hotelRes.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.hotelRes.dto.MessageResponseDTO;
import com.webdev.hotelRes.dto.UpdatePasswordDTO;
import com.webdev.hotelRes.entity.User;
import com.webdev.hotelRes.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService; 

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try{
            
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("User not found: " + id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        try{
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("User not found: " + id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: " + e.getMessage()));
        }
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO){
        try{
            return ResponseEntity.ok(userService.updatePassword(updatePasswordDTO));
        } catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("User not found: " + updatePasswordDTO.getId()));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok(new MessageResponseDTO("User deleted successfully"));
        } catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("User not found: " + id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponseDTO("Error: " + e.getMessage()));
        }
    }


    
}
