package org.osc.oss.confoo.core.speaker

import org.springframework.data.jpa.repository.JpaRepository

interface SpeakerRepository : JpaRepository<Speaker, Long>{

    fun findByOrganizerId(organizerId: Long) : List<Speaker>
}