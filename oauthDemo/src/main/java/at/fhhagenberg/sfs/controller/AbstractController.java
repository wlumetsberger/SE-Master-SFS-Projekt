package at.fhhagenberg.sfs.controller;

import at.fhhagenberg.sfs.model.UserContext;
import at.fhhagenberg.sfs.util.SecurityUtil;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The base controller which holds the common resources which each controller depends on.
 *
 * @author Thomas Herzog <t.herzog@curecomp.com>
 * @since 01/23/17
 */
public abstract class AbstractController {

    protected UserContext utx;

    /**
     * Initialize the UserContext depending on the current security context held authentication.
     */
    public AbstractController() {
        utx = SecurityUtil.createUserCtx(SecurityContextHolder.getContext().getAuthentication());
    }
}
