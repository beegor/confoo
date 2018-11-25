package org.osc.oss.confoo.core.speaker

import org.osc.oss.confoo.core.organizer.Organizer
import javax.persistence.*

@Entity
@Table(name = "speaker")
class Speaker (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speaker_seq")
        @SequenceGenerator(name = "speaker_seq", sequenceName = "speaker_seq", allocationSize = 10)
        val id: Long = 0,

        @Column(name = "full_name")
        val name: String,

        @ManyToOne
        @JoinColumn(name = "organizer_id")
        val organizer: Organizer,

        @Column(name = "short_bio")
        val bio: String,

        @Column(name = "image_path")
        val imagePath: String?

)