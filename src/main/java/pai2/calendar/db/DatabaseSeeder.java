package pai2.calendar.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pai2.calendar.model.EventModel;
import pai2.calendar.model.UserModel;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "calendar.db.recreate", havingValue = "true")
public class DatabaseSeeder implements CommandLineRunner {

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(EventRepository eventRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {
        eventRepository.deleteAll();
        userRepository.deleteAll();

        List<EventModel> events = new ArrayList<>();
        List<UserModel> users = new ArrayList<>();

        // Example users
        users.add(new UserModel("alb",passwordEncoder.encode("alb2"),"alb@gmail.com"));
        users.add(new UserModel("admin",passwordEncoder.encode("a123"),"adm@onet.pl"));

        userRepository.saveAll(users);

        // Example events
        events.add(new EventModel("Zakupy", "2019-06-19", "2019-06-20T12:00:00", users.get(0)));
        events.add(new EventModel("Obiad", "2019-06-20", "2019-06-20", users.get(0)));
        events.add(new EventModel("Praca domowa", "2019-06-22T15:00:00", "2019-06-22T20:00:00", users.get(1)));

        eventRepository.saveAll(events);
    }
}
