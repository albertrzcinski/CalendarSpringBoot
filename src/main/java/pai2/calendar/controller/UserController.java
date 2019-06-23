package pai2.calendar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pai2.calendar.db.UserRepository;
import pai2.calendar.model.UserModel;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin
public class UserController {

    private UserRepository userRepository;

    private UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("all")
    public List<UserModel> getAll(){
        return userRepository.findAll();
    }
}
