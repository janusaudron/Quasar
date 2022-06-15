package co.com.meli.quasar.business;

import co.com.meli.quasar.business.domain.rest.PositionRequest;
import co.com.meli.quasar.business.domain.rest.PositionResponse;

/**
 * The Interface IQuasarService.
 * @author Oscar Ortiz
 */
public interface IQuasarService {
	
	/**
	 * Gets the location and the message.
	 *
	 * @param request the request
	 * @return the location message
	 */
	public PositionResponse getLocationMessage(PositionRequest request);

}
