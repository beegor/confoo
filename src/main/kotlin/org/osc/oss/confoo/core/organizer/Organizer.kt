package org.osc.oss.confoo.core.organizer

import org.osc.oss.confoo.core.user.User
import javax.persistence.*

@Entity
@Table(name = "organizer")
class Organizer(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organizer_seq")
        @SequenceGenerator(name = "organizer_seq", sequenceName = "organizer_seq", allocationSize = 10)
        val id: Long = 0,

        @Column(name = "organizer_name")
        val organizerName: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "user_id")
        val user: User
)
