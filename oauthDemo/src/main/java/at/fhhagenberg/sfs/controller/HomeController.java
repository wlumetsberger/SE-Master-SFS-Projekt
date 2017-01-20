package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.model.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Wolfgang on 09.01.2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String  home(final Principal p, final ModelMap model ){
       model.clear();
       model.addAttribute("userCtx", UserContext.createUserCtxForPrincipal(p));

       return "home";
    }
}
