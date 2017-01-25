package at.fhhagenberg.sfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wolfgang on 09.01.2017.
 */
@Controller
public class HomeController extends AbstractController {

    /**
     * Mapping so that we have a UserContext in the landing view as well.
     *
     * @return 'landing' view name
     */
    @RequestMapping(path = {"/", "/landing"})
    public String index() {
        return "landing";
    }

    @RequestMapping("/home")
    public String home() {

        return "home";
    }
}
