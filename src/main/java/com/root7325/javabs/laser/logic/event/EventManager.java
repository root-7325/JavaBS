package com.root7325.javabs.laser.logic.event;

import com.google.inject.Singleton;
import com.root7325.javabs.utils.LaserByteBuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author root7325 on 26.06.2025
 */
@Singleton
public class EventManager {
    private final List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();

        // TODO: generate with game assets
        this.events.add(new Event(1, 7));
        this.events.add(new Event(2, 14));
        this.events.add(new Event(3, 24));
        this.events.add(new Event(4, 47));
    }

    public void encode(LaserByteBuf out) {
        out.writeVInt(this.events.size());
        for (Event event : this.events) {
            event.encode(out);
        }
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(this.events);
    }
}