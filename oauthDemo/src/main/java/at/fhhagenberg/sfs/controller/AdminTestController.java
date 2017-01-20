package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.model.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Wolfgang on 09.01.2017.
 */
@Controller
public class AdminTestController {

    @RequestMapping("/admintest")
    public String  home(final Authentication auth, final ModelMap model ){
       model.clear();
       model.addAttribute("data",auth);

       return "admintest";
    }
}
