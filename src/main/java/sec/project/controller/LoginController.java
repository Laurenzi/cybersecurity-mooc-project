/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author laurenzo
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }
}
