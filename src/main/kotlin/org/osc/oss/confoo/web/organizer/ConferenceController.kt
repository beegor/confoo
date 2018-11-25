package org.osc.oss.confoo.web.organizer

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/organizer/conference")
class ConferenceController(private val conferenceManager: ConferenceManager) {

    @GetMapping("/list")
    fun listConferences(model: Model): String {
        model.addAttribute("conferences", conferenceManager.getConferenceList(2))
        return "organizer/conference/list"
    }
}