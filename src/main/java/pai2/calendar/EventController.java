package pai2.calendar;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "events")
@CrossOrigin
public class EventController {

    private EventRepository eventRepository;

    public EventController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/all")
    public List<EventModel> getAll(){
        return eventRepository.findAll();
    }

    @RequestMapping("/{id}")
    public Optional<EventModel> getEventById(@PathVariable long id) {return eventRepository.findById(id); }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<EventModel> save(@RequestBody EventModel eventModel){
        eventRepository.save(eventModel);

        return eventRepository.findAll();
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable long id){
        eventRepository.deleteById(id);
    }
}
