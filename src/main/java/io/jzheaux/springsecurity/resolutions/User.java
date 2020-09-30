package io.jzheaux.springsecurity.resolutions;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;
    @Column
    String username;
    @Column
    String password;
    @Column
    boolean enabled = true;
    @OneToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    Collection<UserAuthority> userAuthorities = new ArrayList<>();

    public User() {}

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void grantAuthority(String authority) {
        UserAuthority userAuthority = new UserAuthority(this, authority);
        this.userAuthorities.add(userAuthority);
    }
}
