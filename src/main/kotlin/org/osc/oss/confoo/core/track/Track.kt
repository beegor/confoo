package org.osc.oss.confoo.core.track

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Track (

        @Column(name="track_index")
        val index: Int,

        @Column(name = "track_name")
        val name: String
)