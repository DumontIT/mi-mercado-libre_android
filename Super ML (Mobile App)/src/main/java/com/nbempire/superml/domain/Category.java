package com.nbempire.superml.domain;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 27/05/14, at 21:19.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class Category extends Filter {

    /**
     * The serialVersionUID of this class.
     */
    private static final long serialVersionUID = -4905235544863318400L;

    private AppliedFilter[] pathFromRoot;

    public Category(String id, String name, AppliedFilter[] pathFromRoot) {
        super(id, name);
        this.pathFromRoot = pathFromRoot;
    }

    public void setPathFromRoot(AppliedFilter[] pathFromRoot) {
        this.pathFromRoot = pathFromRoot;
    }
}
