
package co.com.meli.quasar.business.domain.rest;

/**
 * The Class PositionResponse.
 * @author Oscar Ortiz
 */
public class PositionResponse {

    /** The position. */
    private Position position;
    
    /** The message. */
    private String message;

    /**
     * Gets the position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position.
     *
     * @param position the new position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
