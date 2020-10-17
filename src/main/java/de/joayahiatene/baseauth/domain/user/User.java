package de.joayahiatene.baseauth.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    private String userName;

    private String firstName;

    public User(String userName, String firstName, String lastName, String password, String email, List<String> role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.roles.addAll(role);
    }

    private String lastName;

    @JsonIgnore
    private String password;

    private String email;

    @JsonIgnore
    @ElementCollection
    private List<String> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (final String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    public void addRole(final String role) {
        if (this.roles.contains(role)) {
            Logger.getAnonymousLogger().info("role already existing");
        } else {
            this.roles.add(role);
        }
    }

}
