package org.osc.oss.confoo.core.speaker

import org.springframework.stereotype.Service


@Service
class DefaultSpeakerManager(private val repository: SpeakerRepository) : SpeakerManager {

    override fun getSpeakers(organizerId: Long): List<Speaker> {
        return repository.findByOrganizerId(organizerId)
    }

    override fun save(speaker: Speaker): Speaker {
        return repository.save(speaker)
    }
}