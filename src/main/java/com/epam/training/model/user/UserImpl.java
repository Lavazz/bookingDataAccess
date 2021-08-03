package com.epam.training.model.user;

import com.epam.training.model.ticket.Ticket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class UserImpl implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = UserAccount.class)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Ticket> tickets = new LinkedHashSet<>();


    public UserImpl(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserImpl(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
