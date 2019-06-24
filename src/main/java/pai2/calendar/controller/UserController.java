package pai2.calendar.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pai2.calendar.db.UserRepository;
import pai2.calendar.model.UserModel;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private UserController(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("all")
    public List<UserModel> getAll(){
        return userRepository.findAll();
    }

    @PostMapping("save")
    public List<UserModel> save(@RequestBody UserModel userModel){
        UserModel userModelTmp = userModel;
        userModelTmp.setPassword(passwordEncoder.encode(userModel.getPassword()));
        this.userRepository.save(userModelTmp);
        return this.userRepository.findAll();
    }
}
