package org.osc.oss.confoo.web.organizer

import org.osc.oss.confoo.core.organizer.Organizer
import org.osc.oss.confoo.core.organizer.OrganizerManager
import org.osc.oss.confoo.core.user.Role
import org.osc.oss.confoo.core.user.User
import org.osc.oss.confoo.dto.OrganizerDTO
import org.osc.oss.confoo.dto.UserDTO
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@SessionAttributes("organizer")
class OrganizerRegistrationController(private val organizerManager: OrganizerManager,
                                      private val passwordEncoder: PasswordEncoder,
                                      private val authenticationManager: AuthenticationManager) {

    @GetMapping("/organizer/register")
    fun showRegistrationForm(model: Model): String {

        val organizer = OrganizerDTO(0, "",  UserDTO(0, "", "", "", Role.ORGANIZER))
        model.addAttribute("organizer", organizer)
        return "organizer/register"
    }

    @PostMapping("/organizer/register")
    fun processRegistrationForm (@ModelAttribute("organizer") dto: OrganizerDTO,
                                 @RequestParam password: String,
                                 @RequestParam passwordRetyped: String,
                                 model: Model): String {

        val encodedPassword = passwordEncoder.encode(password)
        val user = User (
                0,
                dto.user.login,
                encodedPassword,
                dto.user.fullName,
                Role.ORGANIZER
        )

        var organizer = Organizer(0, dto.organizerName, user)
        organizerManager.save(organizer)

        val authReq = UsernamePasswordAuthenticationToken(user, password)
        val auth = authenticationManager.authenticate(authReq)
        val sc = SecurityContextHolder.getContext()
        sc.authentication = auth


        return "redirect:/organizer/conference/list"
    }
}