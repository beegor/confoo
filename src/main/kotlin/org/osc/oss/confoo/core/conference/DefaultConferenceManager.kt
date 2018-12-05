package org.osc.oss.confoo.core.conference

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

    override fun getConferenceList(organizerId: Long): List<Conference> {
        return repository.findByOrganizerId(organizerId)
    }

    override fun getConferenceList(from: LocalDate, to: LocalDate): List<Conference> {
        return repository.findByStartDateBetween(from, to)
    }
}