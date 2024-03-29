package de.joayahiatene.baseauth.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Serial
    private static final long serialVersionUID = 42L;

    @Id
    private String username;

    private String firstname;

    private String lastname;

    @JsonIgnore
    private String password;

    private String email;

    private String hash;

    private UUID profilePictureID;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public User(final String username, final String firstname, final String lastname,
                final String password, final List<String> role, final String email, String hash) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.hash = hash;
        this.roles.addAll(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (final String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

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


    public void addRole(final String role) {
        if (roles == null) {
            this.roles = new ArrayList<>();
        }

        this.roles.add(role);
    }

}

