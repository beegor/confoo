package org.osc.oss.confoo.core.conference

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ConferenceRepository : JpaRepository<Conference,Long> {

    fun findByOrganizerId(organizerId: Long): List<Conference>

    fun findByStartDateBetween(from: LocalDate, to: LocalDate): List<Conference>
}