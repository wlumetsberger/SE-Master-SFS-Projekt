package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.SessionStorage;
import at.fhhagenberg.sfs.model.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Thomas Herzog<S1610454013@students.fh-hagtenberg.at>
 */
@Controller
public class AdminController extends AbstractController {

    @Autowired
    private SessionStorage storage;

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String home(final ModelMap model) {
        model.remove("projects");
        model.remove("model");
        model.remove("edit");
        model.addAttribute("edit",false);
        model.addAttribute("model", new ProjectModel());
        return "create";
    }

    @RequestMapping(value = "/admin/list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        return "listing";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.POST)
    public String create(@ModelAttribute ProjectModel projectModel,
                         final ModelMap model) {
        storage.add(projectModel);
        model.clear();
        return "redirect:/admin/list";
    }

    @RequestMapping(value="/admin/edit", method = RequestMethod.POST)
    public String edit(@RequestParam("name") String name, final ModelMap model) {
        model.remove("model");
        model.remove("projects");
        model.remove("edit");
        model.addAttribute("edit",true);
        model.addAttribute("model",storage.getByKey(name));
        return "create";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("name") String name,final ModelMap model) {
        storage.remove(name);
        model.remove("edit");
        model.clear();
        return "redirect:/admin/list";
    }

    @ModelAttribute("projects")
    public List<ProjectModel> getProjectModels() {
        List<ProjectModel> models = storage.getData();
        return storage.getData();
    }
}
