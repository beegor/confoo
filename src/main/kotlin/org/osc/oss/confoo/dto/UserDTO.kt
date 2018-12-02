package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.user.Role
import org.osc.oss.confoo.core.user.User

class UserDTO (

        val id: Long,

        var login: String,

        val encodedPassword: String,

        var fullName: String,

        val role: Role
){
    constructor(user: User) : this(user.id, user.login, user.password, user.fullName, user.role)

    companion object {
        fun empty(role: Role): UserDTO = UserDTO(0, "", "", "", role)
    }
}