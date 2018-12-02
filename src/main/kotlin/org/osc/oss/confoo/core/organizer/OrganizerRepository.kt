package org.osc.oss.confoo.core.organizer

import org.springframework.data.jpa.repository.JpaRepository

interface OrganizerRepository : JpaRepository<Organizer, Long> {

    fun findByUserId(userId:Long) : Organizer?
}