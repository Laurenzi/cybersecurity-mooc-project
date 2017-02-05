package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));
        return "done";
    }

    @RequestMapping(value = "/signups/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteSignup(@PathVariable Long id) {
        try {
            signupRepository.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String loadSignups(Model model) {
        model.addAttribute("signups", signupRepository.findAll());
        return "admin";
    }

}
