package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Endpoint for registering a new event
    @PostMapping("/postevent")
    public String eventRegistration(@ModelAttribute Event event) {
        eventRepository.save(event);
        return "redirect:/events";
    }

    // Endpoint for registering a participant to an event
    @PostMapping("/registerParticipant")
    public String registerParticipant(@RequestParam Long eventId, @RequestParam String participantName) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + eventId));
        
        event.getParticipants().add(participantName);
        eventRepository.save(event);
        
        return "redirect:/events";
    }

    // Endpoint for displaying all events with optional filtering
    @GetMapping("/events")
    public String showAllEvents(
            @RequestParam(required = false) String filterTitle,
            @RequestParam(required = false) String filterDescription,
            @RequestParam(required = false) String filterLocation,
            Model model) {
        
        List<Event> allEvents = eventRepository.findAll();

        // Filter by title, description, or location if provided
        if (filterTitle != null && !filterTitle.isEmpty()) {
            allEvents = allEvents.stream()
                    .filter(event -> event.getTitle().toLowerCase().contains(filterTitle.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (filterDescription != null && !filterDescription.isEmpty()) {
            allEvents = allEvents.stream()
                    .filter(event -> event.getDescription().toLowerCase().contains(filterDescription.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (filterLocation != null && !filterLocation.isEmpty()) {
            allEvents = allEvents.stream()
                    .filter(event -> event.getLocation().toLowerCase().contains(filterLocation.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("events", allEvents);
        model.addAttribute("formatter", formatter); // Pass the formatter to the model

        return "events";
    }
}
