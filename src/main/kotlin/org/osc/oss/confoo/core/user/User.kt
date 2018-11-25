package org.osc.oss.confoo.core.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "users")
class User (

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
        @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 10)
        val id: Long = 0,

        @Column(name = "login", unique = true)
        val login: String,

        @Column(name = "encoded_password")
        val encodedPassword: String,

        @Column(name = "full_name")
        val fullName: String,

        @Column(name = "user_role")
        @Enumerated(EnumType.STRING)
        val role: Role

) : UserDetails {


    override fun getUsername(): String {
        return login
    }

    override fun getPassword(): String {
        return encodedPassword
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority("""ROLE_${role.name}"""))
    }

    override fun isEnabled(): Boolean {
        return true
    }


    override fun isCredentialsNonExpired(): Boolean {
        return true
    }


    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}