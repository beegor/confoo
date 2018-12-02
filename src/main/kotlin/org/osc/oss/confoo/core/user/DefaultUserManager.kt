package org.osc.oss.confoo.core.user

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class DefaultUserManager(private val repository: UserRepository) : UserManager {

    override fun loadUserByUsername(username: String): UserDetails {
        val user =  repository.findByLogin(username)
        if (user != null)
            return user
        else throw UsernameNotFoundException("No user with username $username")
    }

    override fun getUser(userId: Long): User? {
        return repository.findById(userId).orElse(null)
    }

    override fun save(user: User) : User {
        return repository.save(user)
    }

    override fun getAllUsers(pageable: Pageable) : Page<User> {
        return repository.findAll(pageable)
    }

    override fun delete(userId: Long){
        repository.deleteById(userId)
    }

    override fun getLoggedInUser(): User? {
        val auth = SecurityContextHolder.getContext().authentication
        if(auth.principal != null && auth.principal is User)
            return auth.principal as User
        return null
    }
}