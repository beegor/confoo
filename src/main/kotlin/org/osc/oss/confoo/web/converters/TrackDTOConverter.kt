package org.osc.oss.confoo.web.converters

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.osc.oss.confoo.dto.TrackDTO
import org.springframework.core.convert.converter.Converter

class TrackDTOConverter(private val conferenceManager: ConferenceManager) : Converter<String, TrackDTO> {

    override fun convert(source: String): TrackDTO? {

        if (source == "-1" || source == "-2")
            return null
        val confIdAndTrackIndex = source.split("-")
        val conferenceId = confIdAndTrackIndex[0].toLong()
        val trackIndex = confIdAndTrackIndex[1].toInt()
        val conference = conferenceManager.getConference(conferenceId)

        return if (conference != null && conference.tracks.size > trackIndex)
            TrackDTO( conference.tracks[trackIndex])
        else null
    }
}