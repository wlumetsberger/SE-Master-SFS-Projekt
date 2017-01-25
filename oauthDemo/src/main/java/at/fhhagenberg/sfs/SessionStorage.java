package at.fhhagenberg.sfs;

import at.fhhagenberg.sfs.model.ProjectModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/13/17
 */
@Component()
// We need to proxy this instance, because session bean could not exists on access because session scoped.
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionStorage implements Serializable {

    private final List<ProjectModel> data;

    public SessionStorage() {
        data = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new ProjectModel("Project_name_" + i, "Project_description_" + i));
        }
    }

    public void remove(ProjectModel model) {
        Objects.requireNonNull(model);
        data.remove(model);
    }

    public void remove(String name) {
        final ProjectModel model = data.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (model != null) {
            remove(model);
        }
    }

    public void add(ProjectModel model) {
        Objects.requireNonNull(model, "Model must no be null");
        if (!isSaved(model)) {
            data.add(model);
        }
    }

    public boolean isSaved(ProjectModel model) {
        return data.contains(model);
    }

    public List<ProjectModel> getData() {
        return Collections.unmodifiableList(data);
    }
}
