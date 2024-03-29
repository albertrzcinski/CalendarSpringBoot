package pai2.calendar.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pai2.calendar.model.EventModel;
import pai2.calendar.model.UserModel;

import java.util.Collection;
import java.util.List;

public class MyUser implements UserDetails {
    private UserModel userModel;

    public MyUser(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userModel.getUsername();
    }

    public String getEmail() { return this.userModel.getEmail();}

    public List<EventModel> getEvents() {return this.userModel.getEvents(); }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
