package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.organizer.Organizer
import org.osc.oss.confoo.core.user.Role
import javax.validation.Valid
import javax.validation.constraints.Size

class OrganizerDTO(

        val id: Long,

        @field:Size(min = 2, max = 30, message = "Organizer name must be between 2 and 30 characters long")
        var organizerName: String,

        @field:Valid
        val user: UserDTO
){
    constructor(organizer: Organizer) : this(organizer.id, organizer.organizerName, UserDTO(organizer.user))

    companion object {
        fun empty() : OrganizerDTO = OrganizerDTO(0, "", UserDTO.empty(Role.ORGANIZER))
    }
}