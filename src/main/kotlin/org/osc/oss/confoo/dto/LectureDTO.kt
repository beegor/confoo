package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.lecture.AudienceLevel
import org.osc.oss.confoo.core.lecture.Lecture
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

class LectureDTO (

        val id: Long,

        var track: TrackDTO?,

        var speaker: SpeakerDTO?,

        var title: String,

        var summary: String,

        var room: String,

        @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm")
        var startTime: LocalDateTime,

        var durationMinutes: Int,

        var targetAudienceLevel: AudienceLevel
) {

    constructor(l: Lecture) : this(l.id, TrackDTO(l.track), SpeakerDTO(l.speaker), l.title, l.summary, l.room, l.startTime, l.durationMinutes, l.targetAudienceLevel)

    companion object {
        fun empty() : LectureDTO = LectureDTO(0, null, null, "", "", "",  LocalDateTime.now(), 45, AudienceLevel.BEGINNER)
    }

    fun toLecture(conference: Conference): Lecture {
        val trackFinal = track ?: throw RuntimeException("Track must be selected!")
        val speakerFinal = speaker ?: throw RuntimeException("Speaker must be selected!")
        return Lecture(id, conference, speakerFinal.toSpeaker(conference.organizer), trackFinal.toTrack(), title, summary, room, startTime, durationMinutes, targetAudienceLevel)
    }
}