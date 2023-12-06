package com.operations.booking.controller;

import com.operations.booking.dto.request.UserRequest;
import com.operations.booking.dto.response.UserResponse;
import com.operations.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/users"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createNewUser(userRequest));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
        return ResponseEntity.ok("User updated successfully!");
    }

    @DeleteMapping({"/id/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
    }



