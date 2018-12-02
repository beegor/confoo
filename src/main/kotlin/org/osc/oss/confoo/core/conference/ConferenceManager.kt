package org.osc.oss.confoo.core.conference

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDate

interface ConferenceManager {

    fun getConference(conferenceId: Long): Conference?

    fun save(conference: Conference): Conference

    fun getConferenceList(from: LocalDate, to: LocalDate, pageable: Pageable): Page<Conference>

    fun getConferenceList(organizerId: Long, pageable: Pageable): Page<Conference>
}