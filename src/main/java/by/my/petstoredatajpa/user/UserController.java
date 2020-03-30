package by.my.petstoredatajpa.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private boolean isLogFlag = false;
    private List<UUID> uuidList;

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User user1 = userRepository.save(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestParam("userName") String name, @RequestBody User user) {
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

    @DeleteMapping(path = "/{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userID) {
        Optional<User> deleteUserById = userRepository.deleteUserById(userID);
        if (deleteUserById.isPresent()) {
            return new ResponseEntity<>("User " + deleteUserById.get() + " successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User wasn't deleted", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable String userName) {
        Optional<User> deleteUserByUserName = userRepository.deleteUserByUserName(userName);
        if (deleteUserByUserName.isPresent()) {
            return new ResponseEntity<>("User " + deleteUserByUserName.get() + " successfully deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User wasn't deleted", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<String> login(@RequestParam("userName") String userName,
                                        @RequestParam("password") String password) {
        User byUserName = userRepository.findByUserName(userName);
        if (byUserName == null) {
            return new ResponseEntity<>("User name doesn't exists.", HttpStatus.BAD_REQUEST);
        } else {
            if (byUserName.getPassword().equals(password)) {
                isLogFlag = true;
                UUID uuid = UUID.randomUUID();
                uuidList.add(uuid);
                return new ResponseEntity<>("User successfully logged.", HttpStatus.OK);
            }
            return new ResponseEntity<>("A wrong password.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<String> logout() {
        UUID uuid = UUID.fromString("");
        if (uuidList.contains(uuid)) {
            uuidList.remove(uuid);
            isLogFlag = false;
            return new ResponseEntity<>("User was successfully logout.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Action denied.", HttpStatus.BAD_REQUEST);

    }
}
