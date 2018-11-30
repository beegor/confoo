package org.osc.oss.confoo.web.organizer

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class OrganizerConferenceController(private val conferenceManager: ConferenceManager) {

    @GetMapping("/organizer/conference/list")
    fun listConferences(model: Model,
                        @RequestParam organizerId:Long,
                        pageable: Pageable): String {
        model.addAttribute("conferences", conferenceManager.getConferenceList(organizerId,pageable))
        return "organizer/conference/list"
    }
}