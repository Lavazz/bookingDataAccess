package com.epam.training.model.ticket;

import com.epam.training.model.event.Event;
import com.epam.training.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class TicketImpl implements Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "event_id")
    private long eventId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    private int place;

    public TicketImpl(long userId, long eventId, int place, Category category) {
        this.eventId = eventId;
        this.userId = userId;
        this.category = category;
        this.place = place;
    }

}
