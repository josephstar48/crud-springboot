package com.springcheckpoint.springcheckpoint;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.ListUI;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //Get all users
    public List<UserDTO> allUsers() {

        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            int currentUserId =  userList.get(i).getId();
            String currentUserEmail = userList.get(i).getEmail();
            userDTOList.add(new UserDTO(currentUserId, currentUserEmail));
        }
        return userDTOList;
    }
    //Data Transfer Object (DTO) for users
    public UserDTO postAUser(User user) {
        if(user == null) {
            return null;
        }
        userRepository.save(user);
        return new UserDTO(user.getId(), user.getEmail());
    }

    //Return all users as DTO
    public UserDTO getAUser(Integer id) {
        //If user is not in database return null
        if(!userRepository.existsById(id)) {
            return null;
        } else {
            //Get the user from database and put into User class
            User user = userRepository.getById(id);

            //Take user class from database and transfer into DTO
            return new UserDTO(user.getId(), user.getEmail());
        }
    }

    //Update user, return DTO
    public UserDTO patchAUser(Integer id, User paramUser) {
        //If user is not in database return null
        if(!userRepository.existsById(id) || paramUser == null) {
            return null;
        } else {
            //Get the user from database and put into User class
            User user = userRepository.getById(id);

            //if Email is not null and Email is not blank, update email
            if(paramUser.getEmail() != null || paramUser.getEmail().isEmpty()) {
                user.setEmail(paramUser.getEmail());
            }
            //if pasword is not null or not empty, update password
            if(paramUser.getPassword() != null || paramUser.getPassword().isEmpty()) {
                user.setPassword(paramUser.getPassword());
            }
            //save any changes
            userRepository.save(user);

            //Take user class from database and transfer into DTO
            return new UserDTO(user.getId(), user.getEmail());
        }
    }
}
