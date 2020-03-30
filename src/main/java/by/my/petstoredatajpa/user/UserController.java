package by.my.petstoredatajpa.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> create (@RequestBody User user){
        User user1 = userRepository.save(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser (@RequestParam ("userName") String name, @RequestBody User user){
        User byUserName = userRepository.findByUserName(name);
        byUserName.setEmail(user.getEmail());
        byUserName.setFirstName(user.getFirstName());
        byUserName.setLastName(user.getLastName());
        byUserName.setUserName(user.getUserName());
        byUserName.setPassword(user.getPassword());
        byUserName.setPhone(user.getPhone());
        byUserName.setUserStatus(user.getUserStatus());
        userRepository.save(byUserName);
        return new ResponseEntity<>(byUserName, HttpStatus.OK);
    }
}
