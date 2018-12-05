package org.osc.oss.confoo.web.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.osc.oss.confoo.core.conference.ConferenceManager
import org.osc.oss.confoo.dto.ConferenceDTO
import org.osc.oss.confoo.web.NoSuchResourceException
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@Api
@RestController
class ConferenceController(private val conferenceManager: ConferenceManager) {


    @ApiOperation(value = "List all conferences starting between given dates. Results are paginated.")
    @GetMapping("/api/conference/list")
    fun listConferences(model: Model,
                        @ApiParam(value = "Date, format: YYYY-MM-DD ", required = true) @RequestParam from: LocalDate,
                        @ApiParam(value = "Date, format: YYYY-MM-DD ", required = true) @RequestParam to: LocalDate
    ): List<ConferenceDTO> {
        val conferenceList = conferenceManager.getConferenceList(from, to)
        return conferenceList.map { ConferenceDTO(it) }
    }


    @ApiOperation(value = "Show conferences details.")
    @GetMapping("/api/conference/{conferenceId}")
    fun showConferenceDetails(model: Model,
                              @ApiParam(value = "Conference id", required = true) @PathVariable conferenceId: Long
    ): ConferenceDTO {
        val conference = conferenceManager.getConference(conferenceId) ?: throw NoSuchResourceException()
        return ConferenceDTO(conference)
    }


}