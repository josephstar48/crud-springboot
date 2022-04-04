package com.springcheckpoint.springcheckpoint;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/users/{id}")
    public UserCount returnUserCount(@PathVariable int id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/users/authenticate")
    public Object returnUserAuthentication(@RequestBody User user) {
        return userService.userAuthentication(user);
    }
}
