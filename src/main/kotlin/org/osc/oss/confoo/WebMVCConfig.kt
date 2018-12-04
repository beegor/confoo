package org.osc.oss.confoo

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.osc.oss.confoo.core.speaker.SpeakerManager
import org.osc.oss.confoo.web.converters.SpeakerDTOConverter
import org.osc.oss.confoo.web.converters.TrackDTOConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig(private val speakerManager: SpeakerManager,
                private val conferenceManager: ConferenceManager) : WebMvcConfigurer {


    override fun addFormatters(registry: FormatterRegistry) {
        super.addFormatters(registry)
        registry.addConverter(SpeakerDTOConverter(speakerManager))
        registry.addConverter(TrackDTOConverter(conferenceManager))
    }
}