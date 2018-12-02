package org.osc.oss.confoo.web.organizer

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.osc.oss.confoo.core.organizer.OrganizerManager
import org.osc.oss.confoo.core.user.UserManager
import org.osc.oss.confoo.dto.ConferenceDTO
import org.osc.oss.confoo.dto.OrganizerDTO
import org.osc.oss.confoo.web.ForbiddenException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus

@Controller
@SessionAttributes("conference")
class OrganizerConferenceController(private val conferenceManager: ConferenceManager,
                                    private val organizerManager: OrganizerManager,
                                    private val userManager: UserManager) {

    @GetMapping("/organizer/conference/list")
    fun listConferences (model: Model,
                        @RequestParam(required = false) page: Int?): String {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        model.addAttribute("organizer", organizer)
        val conferenceList = conferenceManager.getConferenceList(organizer.id, PageRequest.of(page ?: 0, 10))
        model.addAttribute("conferences", conferenceList.map { ConferenceDTO(it) })
        return "organizer/conference/list"
    }



    @GetMapping("/organizer/conference/add")
    fun showAddConferenceForm (model: Model): String {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        model.addAttribute("conference", ConferenceDTO.empty(OrganizerDTO(organizer)))
        return "organizer/conference/edit"
    }


    @PostMapping("/organizer/conference/add")
    fun processAddConferenceForm (@ModelAttribute("conference")
                                  conferenceDTO: ConferenceDTO,
                                  model: Model,
                                  ss: SessionStatus): String {

        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceDTO.toConference(organizer)
        conferenceManager.save(conference)
        ss.setComplete()
        return "redirect:/organizer/conference/list"
    }


    @GetMapping("/organizer/conference/edit/{conferenceId}")
    fun showEditConferenceForm (@PathVariable conferenceId: Long,
                                model: Model): String {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceManager.getConference(conferenceId)
        if (conference != null) {
            if (conference.organizer.id != organizer.id)
                throw ForbiddenException()
            model.addAttribute("conference", ConferenceDTO(conference))
        }
        return "organizer/conference/edit"
    }


    @PostMapping("/organizer/conference/edit/{conferenceId}")
    fun processEditConferenceForm(@ModelAttribute("conference") conferenceDTO: ConferenceDTO,
                                  @PathVariable conferenceId : Long,
                                  model: Model,
                                  ss: SessionStatus): String {

        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceDTO.toConference(organizer)
        conferenceManager.save(conference)
        return "redirect:/organizer/conference/list"
    }

}