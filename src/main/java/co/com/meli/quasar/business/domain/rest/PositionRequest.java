
package co.com.meli.quasar.business.domain.rest;

import java.util.List;

/**
 * The Class PositionRequest.
 * @author Oscar Ortiz
 */
public class PositionRequest {

    /** The satellites. */
    private List<Satellite> satellites = null;

    /**
     * Gets the satellites.
     *
     * @return the satellites
     */
    public List<Satellite> getSatellites() {
        return satellites;
    }

    /**
     * Sets the satellites.
     *
     * @param satellites the new satellites
     */
    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

}
