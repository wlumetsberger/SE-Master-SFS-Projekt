package at.fhhagenberg.sfs;

import at.fhhagenberg.sfs.model.ProjectModel;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

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
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionStorage {

    private final List<ProjectModel> data = new LinkedList<>();

    public SessionStorage() {
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

    public void add(ProjectModel model){
        data.add(model);
    }

    public boolean isSaved(ProjectModel model) {
        return data.contains(model);
    }

    public List<ProjectModel> getData() {
        return Collections.unmodifiableList(data);
    }
}
