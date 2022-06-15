
package co.com.meli.quasar.business.domain.rest;

import java.util.List;

/**
 * The Class Satellite.
 * @author Oscar Ortiz
 */
public class Satellite {

    /** The name. */
    private String name;
    
    /** The distance. */
    private Float distance;
    
    /** The message. */
    private List<String> message = null;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the distance.
     *
     * @return the distance
     */
    public Float getDistance() {
        return distance;
    }

    /**
     * Sets the distance.
     *
     * @param distance the new distance
     */
    public void setDistance(Float distance) {
        this.distance = distance;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public List<String> getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(List<String> message) {
        this.message = message;
    }

}
