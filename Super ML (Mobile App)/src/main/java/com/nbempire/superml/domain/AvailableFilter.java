package com.nbempire.superml.domain;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 27/05/14, at 21:42.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 * @since 1.
 */
public class AvailableFilter extends Filter {

    /**
     * The serialVersionUID of this class
     */
    private static final long serialVersionUID = -1107769475924997735L;

    private AvailableFilter[] possibleValues;

    private Integer results;

    public AvailableFilter(String id, String name, Integer results) {
        super(id, name);
        this.results = results;
    }

    public AvailableFilter(String id, String name, AvailableFilter[] possibleValues) {
        super(id, name);
        this.possibleValues = possibleValues;
    }

    public AvailableFilter[] getPossibleValues() {
        return possibleValues;
    }

    public Integer getResults() {
        return results;
    }
}
