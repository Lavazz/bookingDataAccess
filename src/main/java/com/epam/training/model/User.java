package com.epam.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
@Data
@NoArgsConstructor
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

//    @OneToOne(cascade = CascadeType.ALL, targetEntity = UserAccount.class)
    //   @JoinColumn(name = "user_account_id")
    //   private UserAccount userAccount;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<Ticket> tickets = new LinkedHashSet<>();


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}


