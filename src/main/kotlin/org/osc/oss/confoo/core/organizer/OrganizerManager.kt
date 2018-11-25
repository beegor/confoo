package org.osc.oss.confoo.core.organizer

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface OrganizerManager {
    fun getOrganizer (organizerId: Long) : Organizer?
    fun save(organizer: Organizer): Organizer
    fun delete(organizerId: Long)
    fun getAllOrganizers(pageable: Pageable) : Page<Organizer>


}