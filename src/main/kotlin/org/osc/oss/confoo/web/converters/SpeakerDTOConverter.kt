package org.osc.oss.confoo.web.converters

import org.osc.oss.confoo.core.speaker.SpeakerManager
import org.osc.oss.confoo.dto.SpeakerDTO
import org.springframework.core.convert.converter.Converter

class SpeakerDTOConverter(private val speakerManager: SpeakerManager) : Converter<String, SpeakerDTO> {

    override fun convert(source: String): SpeakerDTO? {
        val id = source.toLong()
        val speaker = speakerManager.getSpeaker(id)
        return if (speaker != null) SpeakerDTO(speaker) else null
    }
}