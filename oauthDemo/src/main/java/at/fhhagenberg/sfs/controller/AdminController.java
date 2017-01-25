package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.SessionStorage;
import at.fhhagenberg.sfs.model.ProjectModel;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Thomas Herzog<S1610454013@students.fh-hagtenberg.at>
 */
@Controller
public class AdminController extends AbstractController {

    @Autowired
    private SessionStorage storage;

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String home(final Model model) {

        model.addAttribute("model", new ProjectModel());

        return "create";
    }
    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String create(@ModelAttribute ProjectModel projectModel, final Model model) {

        model.addAttribute("model", projectModel);

        return "create";
    }

    @ModelAttribute("projects")
    public List<ProjectModel> getProjectModels(){
        return storage.getData();
    }
}
