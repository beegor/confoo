package org.osc.oss.confoo.core.lecture

interface LectureManager {

    fun getLectures(conferenceId: Long): List<Lecture>

    fun save(lecture: Lecture) : Lecture


}