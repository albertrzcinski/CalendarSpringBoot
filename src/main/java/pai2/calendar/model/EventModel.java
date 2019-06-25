package pai2.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String start;
    private String end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserModel userModel;

    public EventModel(){}

    public EventModel(Long id, String title, String start, String end, UserModel userModel){
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.userModel = userModel;
    }

    public EventModel (String title, String start, String end, UserModel userModel){
        this.title = title;
        this.start = start;
        this.end = end;
        this.userModel = userModel;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }
}
