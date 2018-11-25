package org.osc.oss.confoo.core.conference

import org.osc.oss.confoo.core.organizer.Organizer
import java.time.Instant
import javax.persistence.*


@Entity
@Table(name = "conference")
class Conference (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conference_seq")
        @SequenceGenerator(name = "conference_seq", sequenceName = "conference_seq", allocationSize = 10)
        val id: Long = 0,

        @Column
        val name: String,

        @ManyToOne
        @JoinColumn(name = "organizer_id")
        val organizer: Organizer,


        @Column(name = "start_date")
        val startDate: Instant,

        @Column(name = "end_date")
        val endDate: Instant


)