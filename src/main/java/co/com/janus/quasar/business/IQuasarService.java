package co.com.janus.quasar.business;

import co.com.janus.quasar.business.domain.rest.PositionRequest;
import co.com.janus.quasar.business.domain.rest.PositionResponse;

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
