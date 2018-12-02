package org.osc.oss.confoo.core.conference

import org.osc.oss.confoo.core.Address
import org.osc.oss.confoo.core.organizer.Organizer
import org.osc.oss.confoo.core.track.Track
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name = "conference")
class Conference(

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
        val startDate: LocalDate,

        @Column(name = "end_date")
        val endDate: LocalDate,

        @Embedded
        val location: Address,

        @ElementCollection
        @CollectionTable(name="conference_tracks", joinColumns = [JoinColumn(name="conference_id")])
        val tracks: List<Track>

)