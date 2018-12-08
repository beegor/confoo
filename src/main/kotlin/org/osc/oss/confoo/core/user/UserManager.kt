package org.osc.oss.confoo.core.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetailsService

interface UserManager: UserDetailsService {
    fun save(user: User) : User
    fun getAllUsers(pageable: Pageable) : Page<User>
    fun delete(userId: Long)
    fun getUser(userId: Long): User?
    fun getUserByUsername(username: String): User?
    fun getLoggedInUser(): User?
}