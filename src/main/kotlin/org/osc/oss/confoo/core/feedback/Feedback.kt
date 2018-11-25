package org.osc.oss.confoo.core.feedback

import org.osc.oss.confoo.core.lecture.Lecture
import javax.persistence.*


@Entity
@Table(name = "lecture_feedback")
class Feedback (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_seq")
        @SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_seq", allocationSize = 10)
        val id: Long = 0,


        @ManyToOne
        @JoinColumn(name = "lecture_id")
        val lecture: Lecture,


        @Column(name = "lecture_grade")
        @Enumerated(EnumType.STRING)
        val lectureGrade: Grade,


        @Column (name="comment")
        val comment: String

)