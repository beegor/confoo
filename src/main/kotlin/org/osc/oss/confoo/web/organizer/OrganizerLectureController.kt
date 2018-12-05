package org.osc.oss.confoo.web.organizer

import org.osc.oss.confoo.core.conference.ConferenceManager
import org.osc.oss.confoo.core.lecture.LectureManager
import org.osc.oss.confoo.core.organizer.OrganizerManager
import org.osc.oss.confoo.core.speaker.SpeakerManager
import org.osc.oss.confoo.core.user.UserManager
import org.osc.oss.confoo.dto.ConferenceDTO
import org.osc.oss.confoo.dto.LectureDTO
import org.osc.oss.confoo.dto.SpeakerDTO
import org.osc.oss.confoo.dto.TrackDTO
import org.osc.oss.confoo.web.ForbiddenException
import org.osc.oss.confoo.web.NoSuchResourceException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus

@Controller
@SessionAttributes("lecture")
class OrganizerLectureController (private val userManager: UserManager,
                                  private val organizerManager: OrganizerManager,
                                  private val lectureManager: LectureManager,
                                  private val conferenceManager: ConferenceManager,
                                  private val speakerManager: SpeakerManager) {


    @GetMapping("/organizer/conference/{conferenceId}/lecture/add")
    fun showAddLectureForm(
            @PathVariable conferenceId: Long,
            model: Model): String {

        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        if (conference.organizer.id != organizer.id)
            throw ForbiddenException()
        model.addAttribute("lecture", LectureDTO.empty())
        model.addAttribute("conference", ConferenceDTO(conference))
        model.addAttribute("speakers", speakerManager.getSpeakers(organizer.id).map { SpeakerDTO(it) })
        return "organizer/lecture/edit-lecture"
    }

    @PostMapping("/organizer/conference/{conferenceId}/lecture/add")
    fun processAddLectureForm(
            @PathVariable conferenceId: Long,
            @ModelAttribute("lecture") lectureDTO: LectureDTO,
            model: Model, ss: SessionStatus): String {

        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        val lecture = lectureDTO.toLecture(conference)
        lectureManager.save(lecture)
        ss.setComplete()
        return "redirect:/organizer/conference/${conference.id}"
    }


    @GetMapping("/organizer/conference/{conferenceId}/lecture/{lectureId}/edit")
    fun showEditLectureForm(
            @PathVariable conferenceId: Long,
            @PathVariable lectureId: Long,
            model: Model): String {

        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        val lecture = lectureManager.getLecture(lectureId) ?: throw NoSuchResourceException()
        if (lecture.conference.organizer.id != organizer.id)
            throw ForbiddenException()
        model.addAttribute("conference", ConferenceDTO(conference))
        model.addAttribute("lecture", LectureDTO(lecture))
        model.addAttribute("speakers", speakerManager.getSpeakers(organizer.id).map { SpeakerDTO(it) })
        return "organizer/lecture/edit-lecture"
    }

    @PostMapping("/organizer/conference/{conferenceId}/lecture/{lectureId}/edit")
    fun processEditLectureForm(
            @PathVariable lectureId: Long,
            @PathVariable conferenceId: Long,
            @ModelAttribute("lecture") lectureDTO: LectureDTO,
            model: Model, ss: SessionStatus): String {

        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        val lecture = lectureDTO.toLecture(conference)
        lectureManager.save(lecture)
        ss.setComplete()
        return "redirect:/organizer/conference/${conference.id}"
    }



    @PostMapping("/organizer/conference/{conferenceId}/lecture/speaker/add")
    @ResponseBody
    fun processAddSpeakerForm (@ModelAttribute("speaker") speakerDTO: SpeakerDTO,
                               @PathVariable conferenceId: Long

    ) : SpeakerDTO {
        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()
        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        if (conference.organizer.id != organizer.id)
            throw ForbiddenException()
        var speaker = speakerDTO.toSpeaker(organizer)
        speaker = speakerManager.save(speaker)
        return SpeakerDTO(speaker)
    }



    @PostMapping("/organizer/conference/{conferenceId}/lecture/track/add")
    @ResponseBody
    fun processAddTrackForm (@ModelAttribute("track") trackDTO: TrackDTO,
                             @PathVariable conferenceId: Long
    ) : TrackDTO {

        val loggedInUser = userManager.getLoggedInUser() ?: throw ForbiddenException()
        val organizer = organizerManager.getOrganizerForUser(loggedInUser.id) ?: throw ForbiddenException()

        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        if (conference.organizer.id != organizer.id)
            throw ForbiddenException()

        trackDTO.index = conference.tracks.size
        val track = trackDTO.toTrack()
        conference.tracks.add(track)
        conferenceManager.save(conference)
        return TrackDTO(track)
    }


    @ModelAttribute("speaker")
    fun getSpeakerModel() = SpeakerDTO.empty()

    @ModelAttribute("track")
    fun getTrackModel() = TrackDTO.empty()


}