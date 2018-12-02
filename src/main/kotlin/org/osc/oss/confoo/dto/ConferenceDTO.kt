package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.organizer.Organizer
import java.time.LocalDate

class ConferenceDTO (

    val id: Long = 0,

    var name: String,

    val description: String,

    var startDate: LocalDate,

    var endDate: LocalDate,

    val location: AddressDTO,

    val tracks: MutableList<TrackDTO>
) {

    constructor(conference: Conference) : this(conference.id, conference.name, conference.description, conference.startDate, conference.endDate, AddressDTO(conference.location), conference.tracks.map { TrackDTO(it) }.toMutableList())

    companion object {
        fun empty():ConferenceDTO = ConferenceDTO(0, "", "", LocalDate.now(), LocalDate.now(), AddressDTO.empty(), mutableListOf())
    }

    fun toConference(organizer: Organizer) = Conference(id, name, description, organizer, startDate, endDate, location.toAddress(), tracks.map { it.toTrack() }.toMutableList())
}