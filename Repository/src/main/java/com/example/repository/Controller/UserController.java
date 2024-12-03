package com.example.repository.Controller;

import com.example.repository.ApiResponse.ApiResponse;
import com.example.repository.Model.User;
import com.example.repository.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUser() {
        List<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(201).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.status(201).body(new ApiResponse("user deleted successfully"));
    }

    @GetMapping("/check-username-password/username/{username}/password/{password}")
    public ResponseEntity checkUsernamePassword(@PathVariable String username, @PathVariable String password) {
        return ResponseEntity.status(200).body(userService.checkUsernamePassword(username, password));
    }

    @GetMapping("/get/user-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get/users-role/{role}")
    public ResponseEntity getUsersByRole(@PathVariable String role) {
        return ResponseEntity.status(200).body(userService.getUserByRole(role));
    }

    @GetMapping("/get/users-equalorhigherage/{age}")
    public ResponseEntity getUsersByEqualOrHigherage(@PathVariable Integer age) {
        List<User> users=userService.getUsersByAgeGreaterThanEqual(age);
        return ResponseEntity.status(200).body(users);
    }
}
