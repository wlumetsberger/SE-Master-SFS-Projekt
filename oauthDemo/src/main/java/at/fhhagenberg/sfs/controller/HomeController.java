package at.fhhagenberg.sfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Wolfgang on 09.01.2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String  home(final Principal p, final ModelMap model ){
       model.clear();
       model.addAttribute("UserName",p.getName());
       return "/home";
    }
}
