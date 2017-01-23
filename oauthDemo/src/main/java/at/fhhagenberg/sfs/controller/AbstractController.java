package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.model.UserContext;
import at.fhhagenberg.sfs.util.SecurityUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * The base controller which holds the common resources which each controller depends on.
 *
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/23/17
 */
public abstract class AbstractController {

    @ModelAttribute("utx")
    public UserContext getUserContext() {
        return SecurityUtil.createUserCtx(SecurityContextHolder.getContext().getAuthentication());
    }
}
