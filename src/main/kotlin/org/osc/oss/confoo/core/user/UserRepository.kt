package org.osc.oss.confoo.core.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByLogin(login: String) : User?
}