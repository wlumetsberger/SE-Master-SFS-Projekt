package at.fhhagenberg.sfs.util;

import at.fhhagenberg.sfs.model.UserContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;

/**
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/22/17
 */
public class SecurityUtil {

    public static final UserContext createUserCtx(final Authentication auth) {
        if ((auth == null) || (!auth.isAuthenticated())) {
            return new UserContext(false, UserContext.Role.ANONYMOUS, UserContext.Role.ANONYMOUS.name());
        }

        UserContext.Role role = UserContext.Role.USER;
        for (GrantedAuthority ga : auth.getAuthorities()) {
            switch (ga.getAuthority()) {
                case "ADMIN":
                    role = UserContext.Role.ADMIN;
                    break;
                // Maybe user is also admin which will give him more rights than user, so keep on.
                case "USER":
                    role = UserContext.Role.ANONYMOUS;
            }
        }

        return new UserContext(auth.isAuthenticated(), role, auth.getName());
    }
}
