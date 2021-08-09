package com.epam.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ticket", schema = "public")
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    //    @ManyToOne(targetEntity = EventImpl.class)
   // @JoinColumn(name = "event_id")
    @Column(name = "event_id")
    private long eventId;

    //    @ManyToOne(targetEntity = UserImpl.class)
    @Column(name = "user_id")
    private long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private int place;

    public Ticket(long userId, long eventId, int place, Category category) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

}

