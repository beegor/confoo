package org.osc.oss.confoo.web.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.osc.oss.confoo.core.lecture.LectureManager
import org.osc.oss.confoo.dto.LectureDTO
import org.osc.oss.confoo.web.NoSuchResourceException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api
@RestController
class LectureController(private val lectureManager: LectureManager) {

    @ApiOperation("List conference lectures, optionally filtered by track number")
    @GetMapping("/api/conference/{conferenceId}/lectures")
    fun listLectures ( @PathVariable conferenceId: Long,
                       @ApiParam("Track number. Optional.") @RequestParam(required = false) trackNumber: Int?
    ): List<LectureDTO> {

        var lectures = lectureManager.getLectures(conferenceId)
        if (trackNumber != null)
            lectures =  lectures.filter { it.track.index == trackNumber }

        return lectures.map { LectureDTO(it) }
    }

    @ApiOperation("Get  lecture details")
    @GetMapping("/api/conference/lecture/{lectureId}")
    fun showLectureDetails (
            @ApiParam("Lecture id.", required = true) @PathVariable lectureId: Long
    ): LectureDTO {

        var lecture = lectureManager.getLecture(lectureId) ?: throw NoSuchResourceException()
        return LectureDTO(lecture)
    }
}