package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.organizer.Organizer
import org.osc.oss.confoo.core.user.Role

class OrganizerDTO(

        val id: Long,
        var organizerName: String,
        val user: UserDTO
){
    constructor(organizer: Organizer) : this(organizer.id, organizer.organizerName, UserDTO(organizer.user))

    companion object {
        fun empty() : OrganizerDTO = OrganizerDTO(0, "", UserDTO.empty(Role.ORGANIZER))
    }
}