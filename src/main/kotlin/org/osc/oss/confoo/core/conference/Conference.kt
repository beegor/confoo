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

        @Column(name="name")
        val name: String,

        @Column(name = "description")
        val description: String,

        @ManyToOne
        @JoinColumn(name = "organizer_id")
        val organizer: Organizer,

        @Column(name = "start_date")
        val startDate: LocalDate,

        @Column(name = "end_date")
        val endDate: LocalDate,

        @Embedded
        val location: Address,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name="conference_tracks", joinColumns = [JoinColumn(name="conference_id")])
        val tracks: MutableList<Track>

)