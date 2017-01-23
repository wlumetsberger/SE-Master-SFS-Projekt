package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.model.UserContext;
import at.fhhagenberg.sfs.util.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by Wolfgang on 09.01.2017.
 */
@Controller
public class HomeController extends AbstractController {

    @RequestMapping("/home")
    public String home(final ModelMap model) {
        model.clear();
        model.addAttribute("userCtx", utx);

        return "home";
    }
}
