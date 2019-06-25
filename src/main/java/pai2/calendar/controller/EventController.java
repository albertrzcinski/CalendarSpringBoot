package pai2.calendar.controller;

import org.springframework.web.bind.annotation.*;
import pai2.calendar.db.UserRepository;
import pai2.calendar.model.EventModel;
import pai2.calendar.db.EventRepository;
import pai2.calendar.model.EventViewModel;
import pai2.calendar.model.Mapper;
import pai2.calendar.model.UserModel;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "events")
@CrossOrigin
public class EventController {

    private EventRepository eventRepository;
    private UserRepository userRepository;
    private Mapper mapper;

    public EventController(EventRepository eventRepository, UserRepository userRepository, Mapper mapper){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @RequestMapping("/all")
    public List<EventModel> getAll(){
        return eventRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<EventModel> getEventById(@PathVariable long id) {return eventRepository.findById(id); }

    @RequestMapping("/byUser/{username}")
    public List<EventModel> getAllByUserModel(@PathVariable String username) {
        UserModel user = this.userRepository.findByUsername(username);

        return eventRepository.findAllByUserModel(user);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<EventModel> save(@RequestBody EventViewModel eventViewModel){
        EventModel eventModel = this.mapper.mapToEventModel(eventViewModel);
        eventRepository.save(eventModel);

        return eventRepository.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable long id){
        eventRepository.deleteById(id);
    }
}
