package org.osc.oss.confoo.core.conference

interface ConferenceManager {

    fun getConferenceList(organizerId:Long): List<Conference>
}