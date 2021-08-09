package com.epam.training.dao.event;

import com.epam.training.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<Event, Long> {
}
