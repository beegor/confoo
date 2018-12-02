package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.organizer.Organizer
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class ConferenceDTO (

    val id: Long = 0,

    var name: String,

    val organizer: OrganizerDTO,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var startDate: LocalDate,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    var endDate: LocalDate,

    val location: AddressDTO,

    val tracks: List<TrackDTO>
) {

    constructor(conference: Conference) : this(conference.id, conference.name, OrganizerDTO(conference.organizer), conference.startDate, conference.endDate, AddressDTO(conference.location), conference.tracks.map { TrackDTO(it) })

    companion object {
        fun empty(organizer: OrganizerDTO):ConferenceDTO = ConferenceDTO(0, "", organizer, LocalDate.now(), LocalDate.now(), AddressDTO.empty(), listOf())
    }

    fun toConference(organizer: Organizer) = Conference(id, name, organizer, startDate, endDate, location.toAddress(), tracks.map { it.toTrack() })
}