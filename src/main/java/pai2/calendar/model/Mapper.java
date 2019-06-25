package pai2.calendar.model;

import org.springframework.stereotype.Component;
import pai2.calendar.db.UserRepository;

@Component
public class Mapper {

    private UserRepository userRepository;

    public Mapper(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public EventModel mapToEventModel(EventViewModel eventViewModel){
        UserModel userModel = userRepository.findByUsername(eventViewModel.getUsername());
        System.out.println(eventViewModel.getUsername());

        return new EventModel(eventViewModel.getId(), eventViewModel.getTitle(),
                eventViewModel.getStart(),eventViewModel.getEnd(), userModel);
    }
}
