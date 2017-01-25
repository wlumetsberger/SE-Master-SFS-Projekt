package at.fhhagenberg.sfs.model;

import java.util.Objects;

/**
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/13/17
 */
public class SelectItem {

    private String name;

    public SelectItem() {
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectItem that = (SelectItem) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
