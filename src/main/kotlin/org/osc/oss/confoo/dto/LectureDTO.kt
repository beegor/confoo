package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.lecture.AudienceLevel
import org.osc.oss.confoo.core.lecture.Lecture
import org.springframework.format.annotation.DateTimeFormat
import java.lang.RuntimeException
import java.time.Instant
import java.time.LocalDateTime

class LectureDTO (

        val id: Long,

        var trackIndex: Int,

        var speaker: SpeakerDTO?,

        var title: String,

        var summary: String,

        var room: String,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm")
        var startTime: LocalDateTime,

        var durationMinutes: Int,

        var targetAudienceLevel: AudienceLevel
) {

    companion object {
        fun empty() : LectureDTO = LectureDTO(0, -1,null, "", "", "",  LocalDateTime.now(), 45, AudienceLevel.BEGINNER)
    }

    fun toLecture(conference: Conference): Lecture {

        var speakerFinal = speaker

        if (trackIndex < 0)
            throw RuntimeException("Track must be selected!")
        if(speakerFinal == null)
            throw RuntimeException("Speaker must be selected!")

        return Lecture(id, conference, speakerFinal.toSpeaker(conference.organizer), conference.tracks.get(trackIndex), title, summary, room, startTime, durationMinutes, targetAudienceLevel)
    }
}