package org.osc.oss.confoo.core.conference

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DefaultConferenceManager(val repository: ConferenceRepository): ConferenceManager {


    override fun getConference(conferenceId: Long): Conference? {
        return repository.findById(conferenceId).orElse(null)
    }

    override fun save(conference: Conference) : Conference {
        return repository.save(conference)
    }

    override fun getConferenceList(organizerId: Long, pageable: Pageable): Page<Conference> {
        return repository.findByOrganizerId(organizerId, pageable)
    }

    override fun getConferenceList(from: LocalDate, to: LocalDate, pageable: Pageable): Page<Conference> {
        return repository.findByStartDateBetween(from, to, pageable)
    }
}