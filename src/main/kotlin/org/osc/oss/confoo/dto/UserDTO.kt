package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.user.Role

class UserDTO (

        val id: Long,

        var login: String,

        val encodedPassword: String,

        var fullName: String,

        val role: Role
)