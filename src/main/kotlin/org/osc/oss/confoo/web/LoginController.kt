package org.osc.oss.confoo.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class LoginController {


    @GetMapping("/login")
    fun showHome(): String {
        return "common/login"
    }
}