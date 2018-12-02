package org.osc.oss.confoo.core.lecture

import org.springframework.stereotype.Service


@Service
class DefaultLectureManager(val repository: LectureRepository) : LectureManager {

    override fun getLectures(conferenceId: Long): List<Lecture> {
        return repository.findAll()
    }

    override fun save(lecture: Lecture): Lecture {
        return repository.save(lecture)
    }
}