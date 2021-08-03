package com.epam.training.model.event;

import com.epam.training.model.ticket.Ticket;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventImpl implements Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private Date date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Ticket> tickets = new LinkedHashSet<>();

    public EventImpl(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
