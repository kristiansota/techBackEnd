package com.kristian.test.controller;


import com.kristian.test.exception.AppException;
import com.kristian.test.model.AuthenticationRequest;
import com.kristian.test.model.AuthenticationResponse;
import com.kristian.test.model.User;
import com.kristian.test.service.MyUserDetailsService;
import com.kristian.test.service.UserService;
import com.kristian.test.util.JwtUtil;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //GET ALL USERS
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    //USER SIGN UP
    @PostMapping("/users/signup")
    public User addUser(@RequestBody UserDTO userDTO){
        return userService.signUp(userDTO);
    }

    //ADD USER BY ADMIN
    @PostMapping("/users/add_user")
    public User addUserFromAdmin(@RequestBody UserDTO userDTO) {
        return userService.addNewUser(userDTO);
    }

    //UPDATE USER BY ADMIN
    @RequestMapping(value = "/users/update_user/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody UserDTO userDTO,
                           @PathVariable Long userId){
        return userService.updateUser(userDTO,userId);
    }

    //DELETE USER BY ADMIN
    @RequestMapping(value = "/users/delete_user/{userId}", method = RequestMethod.DELETE)
    public User deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }


    //AUTHENTICATE USER
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {

            throw new AppException("Incorrect username or password");
         }
        final UserDetails userDetails = userDetailsService.
                loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
