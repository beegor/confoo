package org.osc.oss.confoo.core.speaker

interface SpeakerManager {

    fun getSpeaker(speakerId: Long) :Speaker?

    fun getSpeakers(organizerId: Long): List<Speaker>

    fun save(speaker: Speaker): Speaker

}