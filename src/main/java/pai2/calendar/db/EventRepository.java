package pai2.calendar.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pai2.calendar.model.EventModel;
import pai2.calendar.model.UserModel;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long> {
    List<EventModel> findAllByUserModel (UserModel userModel);
}
