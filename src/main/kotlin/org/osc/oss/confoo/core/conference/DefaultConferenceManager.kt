package org.osc.oss.confoo.core.conference

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DefaultConferenceManager(val repository: ConferenceRepository): ConferenceManager {

    override fun getConferenceList(organizerId: Long, pageable: Pageable): Page<Conference> {
        return repository.findByOrganizerId(organizerId, pageable)
    }

    override fun getConferenceList(from: Instant, to: Instant, pageable: Pageable): Page<Conference> {
        return repository.findByStartDateBetween(from, to, pageable)
    }
}