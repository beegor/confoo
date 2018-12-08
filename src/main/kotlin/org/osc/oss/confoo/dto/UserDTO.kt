package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.user.Role
import org.osc.oss.confoo.core.user.User
import javax.validation.constraints.Size

class UserDTO (

        val id: Long,

        @field:Size(min = 4, max = 12, message = "User login must be between 4 and 12 characters long")
        var login: String,

        val encodedPassword: String,

        @field:Size(min = 2, max = 30, message = "User full name must be between 4 and 12 characters long")
        var fullName: String,

        val role: Role
){
    constructor(user: User) : this(user.id, user.login, user.password, user.fullName, user.role)

    companion object {
        fun empty(role: Role): UserDTO = UserDTO(0, "", "", "", role)
    }
}