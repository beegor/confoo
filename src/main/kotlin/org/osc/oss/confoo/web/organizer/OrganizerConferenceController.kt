package org.osc.oss.confoo.web.organizer

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.osc.oss.confoo.core.lecture.LectureManager
import org.osc.oss.confoo.core.organizer.OrganizerManager
import org.osc.oss.confoo.core.user.UserManager
import org.osc.oss.confoo.dto.ConferenceDTO
import org.osc.oss.confoo.dto.LectureDTO
import org.osc.oss.confoo.dto.OrganizerDTO
import org.osc.oss.confoo.web.ForbiddenException
import org.osc.oss.confoo.web.NoSuchResourceException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus

@Controller
@SessionAttributes("conference")
class OrganizerConferenceController(private val conferenceManager: ConferenceManager,
                                    private val organizerManager: OrganizerManager,
                                    private var lectureManager: LectureManager,
                                    private val userManager: UserManager) {

    @GetMapping("/organizer/conference/list")
    fun listConferences (model: Model,
                        @RequestParam(required = false) page: Int?): String {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        model.addAttribute("organizer", organizer)
        val conferenceList = conferenceManager.getConferenceList(organizer.id, PageRequest.of(page ?: 0, 10))
        model.addAttribute("conferences", conferenceList.map { ConferenceDTO(it) })
        return "organizer/conference/list-conferences"
    }

    @GetMapping("/organizer/conference/{conferenceId}")
    fun showConferenceDetails (model: Model,
                               @PathVariable conferenceId: Long
    ): String {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        if (conference.organizer.id != organizer.id)
            throw ForbiddenException()
        model.addAttribute("conference", ConferenceDTO(conference))
        model.addAttribute("lectures", lectureManager.getLectures(conference.id).map { LectureDTO(it) })
        return "organizer/conference/conference-details"
    }



    @GetMapping("/organizer/conference/add")
    fun showAddConferenceForm (model: Model): String {
        model.addAttribute("conference", ConferenceDTO.empty())
        return "organizer/conference/edit-conference"
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


    @GetMapping("/organizer/conference/{conferenceId}/edit")
    fun showEditConferenceForm (@PathVariable conferenceId: Long,
                                model: Model): String {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        if (conference.organizer.id != organizer.id)
            throw ForbiddenException()
        model.addAttribute("conference", ConferenceDTO(conference))
        return "organizer/conference/edit-conference"
    }


    @PostMapping("/organizer/conference/{conferenceId}/edit")
    fun processEditConferenceForm(@ModelAttribute("conference") conferenceDTO: ConferenceDTO,
                                  @PathVariable conferenceId : Long,
                                  model: Model,
                                  ss: SessionStatus): String {

        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceDTO.toConference(organizer)
        conferenceManager.save(conference)
        ss.setComplete()
        return "redirect:/organizer/conference/${conference.id}"
    }

}