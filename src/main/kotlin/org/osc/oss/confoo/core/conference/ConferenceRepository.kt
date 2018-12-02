package org.osc.oss.confoo.core.conference

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ConferenceRepository : JpaRepository<Conference,Long> {

    fun findByOrganizerId(organizerId: Long, pageable: Pageable): Page<Conference>

    fun findByStartDateBetween(from: LocalDate, to: LocalDate, pageable: Pageable): Page<Conference>
}