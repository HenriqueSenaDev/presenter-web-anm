package gov.edu.anm.presenter.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.edu.anm.presenter.dto.ParticipationDTO;
import gov.edu.anm.presenter.entities.AppUser;
import gov.edu.anm.presenter.entities.Event;
import gov.edu.anm.presenter.entities.EventRole;
import gov.edu.anm.presenter.entities.Participation;
import gov.edu.anm.presenter.entities.ParticipationPK;
import gov.edu.anm.presenter.repositories.AppUserRepository;
import gov.edu.anm.presenter.repositories.EventRepository;
import gov.edu.anm.presenter.repositories.EventRoleRepository;
import gov.edu.anm.presenter.repositories.ParticipationRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
    private final AppUserRepository appUserRepository;
    private final EventRepository eventRepository;
    private final EventRoleRepository eventRoleRepository;
    private final ParticipationRepository participationRepository;

    // Event methods
    @Override
    public Event findEventById(Long id) {
        return eventRepository.findById(id).get();
    }

    @Override
    public Event findEventByName(String username) {
        return eventRepository.findByName(username);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<ParticipationDTO> findEventParticipations(Long id) {
        Event evt = eventRepository.findById(id).get();
        Set<ParticipationDTO> parts = new HashSet<>();
        evt.getParticipations().forEach(part -> {
            parts.add(new ParticipationDTO(part.getId().getEvent().getName(), part.getId().getUser().getUsername(),
                    part.getEventRole().getName(), null));
        });
        return List.copyOf(parts);
    }

    @Override
    public Event saveEvent(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public Participation addParticipation(Long appUserId, Long eventId, Long eventRoleId, Long teamId) {
        AppUser user = appUserRepository.findById(appUserId).get();
        Event event = eventRepository.findById(eventId).get();
        ParticipationPK participationPK = new ParticipationPK(event, user);
        EventRole eventRole = eventRoleRepository.findById(eventRoleId).get();
        Participation participation = new Participation(participationPK, eventRole, null);
        participationRepository.save(participation);
        return participation;
    }

    @Override
    public Event updateEvent(Event event, Long id) {
        Event evt = eventRepository.findById(id).get();
        if (event.getCode() != null) {
            evt.setCode(event.getCode());
        }
        if (event.getName() != null) {
            evt.setName(event.getName());
        }
        if (event.getJurorCode() != null) {
            evt.setJurorCode(event.getJurorCode());
        }
        if (!event.getParticipations().isEmpty()) {
            evt.setParticipations(event.getParticipations());
        }
        return eventRepository.saveAndFlush(evt);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void deleteParticipation(Long userId, Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        Participation participation = event.getParticipations().stream().filter(
                x -> x.getId().getUser().getId() == userId).findFirst().orElse(null);
        participationRepository.delete(participation);
    }

    // EventRole methods
    @Override
    public EventRole findEventRoleById(Long id) {
        return eventRoleRepository.findById(id).get();
    }

    @Override
    public List<EventRole> findAllEventRoles() {
        return eventRoleRepository.findAll();
    }

    @Override
    public EventRole saveEventRole(EventRole eventRole) {
        return eventRoleRepository.save(eventRole);
    }

    @Override
    public EventRole updateEventRole(EventRole eventRole, Long id) {
        EventRole role = eventRoleRepository.getById(id);
        if (eventRole.getName() != null) {
            role.setName(eventRole.getName());
        }
        return eventRoleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteEventRole(Long id) {
        eventRoleRepository.deleteById(id);
    }
}
