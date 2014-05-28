package com.nbempire.superml.domain;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 27/05/14, at 21:41.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class AppliedFilter extends Filter {

    /**
     * The serialVersionUID of this class
     */
    private static final long serialVersionUID = 5805154923463516376L;

    private AppliedFilter value;

    public AppliedFilter(String id, String name) {
        super(id, name);
    }

    public AppliedFilter(String id, String name, AppliedFilter value) {
        super(id, name);
        this.value = value;
    }
}
