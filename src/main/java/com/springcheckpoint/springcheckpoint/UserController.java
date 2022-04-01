package com.springcheckpoint.springcheckpoint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//     [
//    {
//        "id": 1,
//            "email": "john@example.com"
//    },
//    {
//        "id": 2,
//            "email": "eliza@example.com"
//    }
//  ]


    @GetMapping("/users")
    public Iterable<UserDTO> returnListOfUsers() {
        return userService.allUsers();
    }

    @PostMapping("/users")
    public UserDTO returnUserAfterPost(@RequestBody User user) {
       return userService.postAUser(user);
    }

    @GetMapping("/users/{id}")
    public UserDTO returnUserAfterPost(@PathVariable Integer id) {
        return userService.getAUser(id);
    }

    @PatchMapping(value = "/users/{id}")
    public UserDTO returnUserAfterPatch(
            @PathVariable("id") Integer id,
            @RequestBody(required = false) User user) {
        return userService.patchAUser(id,user);
    }
}
