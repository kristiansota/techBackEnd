package com.kristian.test.service;

import com.kristian.test.model.Roles;
import com.kristian.test.model.User;
import com.kristian.test.repository.RoleRepository;
import com.kristian.test.repository.UserRepository;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }


    public User signUp(UserDTO userDTO){

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());

        verifyRoles();
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(1L))));

        return userRepository.save(user);
    }

    public User addNewUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());

        if (userDTO.getRole().equals("ROLE_USER")){
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(1L))));
        } else {
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(2L))));
        }

        return userRepository.save(user);
    }

    private void verifyRoles(){
        if(!roleRepository.findById(1L).isPresent()) {
            roleRepository.save(new Roles("ROLE_USER"));
        }
        if (!roleRepository.findById(2L).isPresent()) {
            roleRepository.save(new Roles("ROLE_ADMIN"));
        }
    }

    public User updateUser(UserDTO userDTO, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());

        if (userDTO.getRole().equals("ROLE_USER")){
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(1L))));
        } else {
            user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getOne(2L))));
        }

        return userRepository.save(user);
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        user.setTo_date(new Date());
        return userRepository.save(user);
    }
    public String getNameByUsername(String username){
       return userRepository.findByUsername(username).getName();
    }
}
