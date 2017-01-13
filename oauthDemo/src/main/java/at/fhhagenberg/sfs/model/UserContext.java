package at.fhhagenberg.sfs.model;

import java.security.Principal;

/**
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/13/17
 */
public class UserContext {

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

    public static final UserContext createUserCtxForPrincipal(final Principal p) {
        if (p == null) {
            return new UserContext(false, Role.ANONYMOUS, Role.ANONYMOUS.name());
        }
        // TODO: Role must be set correctly, but Principal has no role information
        return new UserContext(true, Role.USER, p.getName());
    }

    public boolean isAdmin(){
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
