package org.osc.oss.confoo.core.conference

import java.time.LocalDate

interface ConferenceManager {

    fun getConference(conferenceId: Long): Conference?

    fun save(conference: Conference): Conference

    fun getConferenceList(from: LocalDate, to: LocalDate): List<Conference>

    fun getConferenceList(organizerId: Long): List<Conference>
}