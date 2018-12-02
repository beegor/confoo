package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.organizer.Organizer
import org.osc.oss.confoo.core.speaker.Speaker

class SpeakerDTO (

    val id: Long,

    var name: String,

    var bio: String,

    var imagePath: String?
) {

    constructor(speaker: Speaker) : this(speaker.id, speaker.name, speaker.bio, speaker.imagePath)

    companion object {
        fun empty() = SpeakerDTO(0, "", "", null)
    }

    fun toSpeaker(organizer: Organizer) = Speaker(id, name, organizer, bio, imagePath)
}