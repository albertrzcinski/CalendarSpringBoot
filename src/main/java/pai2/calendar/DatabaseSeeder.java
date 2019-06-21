package pai2.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "calendar.db.recreate", havingValue = "true")
public class DatabaseSeeder implements CommandLineRunner {

    private EventRepository eventRepository;

    @Autowired
    public DatabaseSeeder(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }


    @Override
    public void run(String... args) {
        eventRepository.deleteAll();

        List<EventModel> events = new ArrayList<>();

        // Example events
        events.add(new EventModel("Zakupy", "2019-06-19", "2019-06-20T12:00:00"));
        events.add(new EventModel("Obiad", "2019-06-20", "2019-06-20"));
        events.add(new EventModel("Praca domowa", "2019-06-22T15:00:00", "2019-06-22T20:00:00"));

        eventRepository.saveAll(events);
    }
}
