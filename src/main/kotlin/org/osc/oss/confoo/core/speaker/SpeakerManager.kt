package org.osc.oss.confoo.core.speaker

interface SpeakerManager {

    fun getSpeakers(organizerId: Long): List<Speaker>

    fun save(speaker: Speaker): Speaker

}