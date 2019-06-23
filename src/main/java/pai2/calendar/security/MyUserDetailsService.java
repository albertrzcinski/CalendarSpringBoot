package pai2.calendar.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pai2.calendar.db.UserRepository;
import pai2.calendar.model.UserModel;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel user = this.userRepository.findByUsername(s);
        MyUser myUser = new MyUser(user);

        return myUser;
    }
}
