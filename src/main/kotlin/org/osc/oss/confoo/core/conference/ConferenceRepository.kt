package org.osc.oss.confoo.core.conference

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant

interface ConferenceRepository : JpaRepository<Conference,Long> {

    fun findByOrganizerId(organizerId: Long, pageable: Pageable): Page<Conference>

    fun findByStartDateBetween(from: Instant, to: Instant, pageable: Pageable): Page<Conference>
}