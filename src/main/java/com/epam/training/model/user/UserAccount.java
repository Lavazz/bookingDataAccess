package com.epam.training.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Data
public class UserAccount  {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @OneToOne(mappedBy = "userAccount")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    User user;

    @Column
    Long amount;
}
