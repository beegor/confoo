package org.osc.oss.confoo.core.conference

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.Instant

interface ConferenceManager {

    fun getConferenceList(from: Instant, to:Instant, pageable: Pageable): Page<Conference>

    fun getConferenceList(organizerId: Long, pageable: Pageable): Page<Conference>
}