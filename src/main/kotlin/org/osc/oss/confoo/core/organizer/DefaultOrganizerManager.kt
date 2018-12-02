package org.osc.oss.confoo.core.organizer

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DefaultOrganizerManager(private val repository: OrganizerRepository) : OrganizerManager {


    override fun getOrganizer (organizerId: Long) : Organizer? {
        return repository.findById(organizerId).orElse(null)
    }

    override fun getOrganizerForUser(userId: Long): Organizer? {
        return repository.findByUserId(userId)
    }

    override fun save(organizer: Organizer): Organizer {
        return repository.save(organizer)
    }

    override fun delete(organizerId: Long) {
        repository.deleteById(organizerId)
    }

    override fun getAllOrganizers(pageable: Pageable) : Page<Organizer> {
        return repository.findAll(pageable)
    }

}