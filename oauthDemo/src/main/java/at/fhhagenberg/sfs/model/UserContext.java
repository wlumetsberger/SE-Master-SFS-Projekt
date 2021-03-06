package at.fhhagenberg.sfs.model;

/**
 * An object of this class represents a user interacting with the application.
 *
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/13/17
 */
public class UserContext {

    /**
     * Defines the supported roles
     */
    public enum Role {
        ADMIN,
        USER,
        ANONYMOUS;
    }

    private final boolean logged;
    private final Role role;
    private final String name;

    public UserContext(boolean logged,
                       Role role,
                       String name) {
        this.logged = logged;
        this.role = role;
        this.name = name;
    }

    public boolean isAdmin() {
        return (isLogged()) && (Role.ADMIN.equals(role));
    }

    public boolean isLogged() {
        return logged;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
