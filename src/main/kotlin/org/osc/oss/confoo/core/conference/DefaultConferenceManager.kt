package org.osc.oss.confoo.core.conference

import org.springframework.stereotype.Service

@Service
class DefaultConferenceManager: ConferenceManager {

    override fun getConferenceList(organizerId: Long): List<Conference> {
        return listOf()
    }
}