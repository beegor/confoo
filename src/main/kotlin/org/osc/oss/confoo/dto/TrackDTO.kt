package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.track.Track


class TrackDTO (

        var index: Int,

        var name: String
) {
    constructor(track: Track): this(track.index, track.name)

    fun toTrack() = Track(index, name)
}