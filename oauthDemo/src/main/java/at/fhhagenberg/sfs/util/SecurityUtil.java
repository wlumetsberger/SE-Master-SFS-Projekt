package at.fhhagenberg.sfs.util;

import at.fhhagenberg.sfs.model.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/22/17
 */
public class SecurityUtil {

    /**
     * Create a UserContext object for the given authentication.
     *
     * @param auth the authentication holding the authentication information
     * @return the create user context object
     */
    public static final UserContext createUserCtx(final Authentication auth) {
        // If not authentication is available the user is always considered to be anonymous
        UserContext.Role role = UserContext.Role.ANONYMOUS;
        String name = UserContext.Role.ANONYMOUS.name();

        if (auth != null) {
            name = ((String) auth.getPrincipal());

            for (GrantedAuthority ga : auth.getAuthorities()) {
                switch (ga.getAuthority()) {
                    case "ROLE_ADMIN":
                        role = UserContext.Role.ADMIN;
                        break;
                    case "ROLE_USER":
                        role = UserContext.Role.USER;
                        break;
                    default:
                        role = UserContext.Role.ANONYMOUS;
                }
                // Cannot have more privileges, therefore can break here
                if (UserContext.Role.ADMIN.equals(role)) {
                    break;
                }
            }
        }

        return new UserContext(!UserContext.Role.ANONYMOUS.equals(role), role, name);
    }
}
