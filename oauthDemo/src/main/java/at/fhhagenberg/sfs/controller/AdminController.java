package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.model.ProjektModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * @author Thomas Herzog<S1610454013@students.fh-hagtenberg.at>
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String home(final Principal p,
                       final Model model) {

        model.addAttribute("model", new ProjektModel());

        return "create";
    }
}
