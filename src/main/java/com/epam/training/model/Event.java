package com.epam.training.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "event", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Date date;

//    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<TicketImpl> tickets = new LinkedHashSet<>();

    public Event(String title, Date date) {
        this.title = title;
        this.date = date;
    }
}
