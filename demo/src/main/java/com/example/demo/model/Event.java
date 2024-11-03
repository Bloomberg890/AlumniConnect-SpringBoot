package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "location", nullable = false)
    private String location;

    @ElementCollection
    @CollectionTable(name = "event_participants", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "participant_name")
    private List<String> participants = new ArrayList<>();

    public Event() {
        // Default constructor required by JPA
    }

    public Event(String title, String description, LocalDateTime eventDate, String location) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
    }

    // Check if a user is already registered
    public boolean isRegistered(String participantName) {
        return participants.contains(participantName);
    }

    
}
