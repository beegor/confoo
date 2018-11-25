package org.osc.oss.confoo.core.lecture

import org.osc.oss.confoo.core.conference.Conference
import org.osc.oss.confoo.core.speaker.Speaker
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "lecture")
class Lecture (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture_seq")
        @SequenceGenerator(name = "lecture_seq", sequenceName = "lecture_seq", allocationSize = 10)
        val id: Long = 0,

        @ManyToOne
        @JoinColumn(name = "conference_id")
        val conference: Conference,

        @ManyToOne
        @JoinColumn(name = "speaker_id")
        val speaker: Speaker,

        @Column(name = "title")
        val title: String,

        @Column(name = "summary")
        val summary: String,


        @Column(name = "room")
        val room: String,

        @Column(name = "start_time")
        val startTime: Instant,

        @Column(name = "duration_minutes")
        val durationMinutes: Int,

        @Column(name = "audience_level")
        val targetAudienceLevel: AudienceLevel

)