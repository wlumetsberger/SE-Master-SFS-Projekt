package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.SessionStorage;
import at.fhhagenberg.sfs.model.ProjectModel;
import at.fhhagenberg.sfs.model.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * @author Thomas Herzog<S1610454013@students.fh-hagtenberg.at>
 */
@Controller
public class AdminController {

    @Autowired
    private SessionStorage storage;

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String home(final Principal p,
                       final Model model) {

        model.addAttribute("model", new ProjectModel());
        model.addAttribute("userCtx", UserContext.createUserCtxForPrincipal(p));

        return "create";
    }
}
