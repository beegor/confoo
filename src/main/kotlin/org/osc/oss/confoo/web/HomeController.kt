package org.osc.oss.confoo.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {


    @GetMapping("/")
    fun showHome(): String {
        return "common/home"
    }
}