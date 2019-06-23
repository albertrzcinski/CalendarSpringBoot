package pai2.calendar.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pai2.calendar.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
