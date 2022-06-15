package co.com.meli.quasar.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.com.meli.quasar.business.domain.Constant;
import co.com.meli.quasar.business.domain.rest.Position;
import co.com.meli.quasar.business.domain.rest.PositionRequest;
import co.com.meli.quasar.business.domain.rest.PositionResponse;
import co.com.meli.quasar.business.domain.rest.Satellite;

/**
 * The Class QuasarService. 
 * @Description Class with business logic
 * @author Oscar Ortiz
 */

public class QuasarService implements IQuasarService {
	
	/**
	 * Gets the location message.
	 *
	 * @param request the service request
	 * @return the location and the message
	 */
	@Override
	public PositionResponse getLocationMessage(PositionRequest request) {
		
		PositionResponse response = null;
		
		//Se organizan los satelites en un Map para facilitar busquedas
		Map<String, Satellite> satelliteMap = new HashMap<>();
		for (Satellite satellite : request.getSatellites()) {
			satelliteMap.put(satellite.getName(), satellite);
		}
		
		
		//Si no llego la informacion de un satelite se genera error
		if(satelliteMap.get(Constant.KENOBI)== null || satelliteMap.get(Constant.SKYWALKER)== null || satelliteMap.get(Constant.SATO)== null) {
			throw new RuntimeException("Informacion incompleta no es posible obtener ubicacion");
		} else {
			Position location = getLocation(satelliteMap);
			String mensaje = getMessage(satelliteMap);
			
			response = new PositionResponse();
			response.setPosition(location);
			response.setMessage(mensaje);
		}
		
		
		return response;
	}
	

	/**
	 * Gets the location.
	 *
	 * @param satelliteMap the satellite map
	 * @return the location
	 */
	private Position getLocation(Map<String, Satellite> satelliteMap) {
		
		Float a = satelliteMap.get(Constant.KENOBI).getDistance();
		Float b = satelliteMap.get(Constant.SKYWALKER).getDistance();
		Float c = satelliteMap.get(Constant.SATO).getDistance();
		
		Float x = calculateX(a, b, c);
		Float y = calculateY(a, b, c);
		
		Position position = new Position();
		position.setX(x);
		position.setY(y);
		
		return position;
		
	}
	
	/**
	 * Calculate X coordinate.
	 * Para obtener esa formula se realizo igualando la formula de 3 circulos y al resolver el sistema se obutuvo ese resultado
	 * @param a the a
	 * @param b the b
	 * @param c the c
	 * @return the float
	 */
	private Float calculateX(Float a, Float b, Float c) {
		Float x = ((2*a*a)+(c*c)-(3*b*b)-780000)/1600;
		return x;
	}
	
	/**
	 * Calculate Y coordinate.
	 * Para obtener esa formula se realizo igualando la formula de 3 circulos y al resolver el sistema se obutuvo ese resultado
	 *
	 * @param a the a
	 * @param b the b
	 * @param c the c
	 * @return the float
	 */
	private Float calculateY(Float a, Float b, Float c) {
		Float y = (a*a/200)-(b*b/200)-1350-((12*a*a) + (6*c*c) - (18*b*b) - 4680000)/1600;
		return y;
	}
	
	/**
	 * Gets the message.
	 *
	 * @param satelliteMap the satellite map
	 * @return the message
	 */
	private String getMessage(Map<String, Satellite> satelliteMap) {
		
		List<String> mensaje1 = satelliteMap.get(Constant.KENOBI).getMessage();
		List<String> mensaje2 = satelliteMap.get(Constant.SKYWALKER).getMessage();
		List<String> mensaje3 = satelliteMap.get(Constant.SATO).getMessage();
		
		int tamano1= mensaje1.size();
		int tamano2= mensaje2.size();
		int tamano3= mensaje3.size();
		int tamanoMensaje = 0;
		
		//int i=0,j=0,k=0;
		int posicionPrimeraPalabra1 = 0;
		int posicionPrimeraPalabra2 = 0;
		int posicionPrimeraPalabra3 = 0;
		
		//busca la posicion de la primera palabra para determinar el tamanio del mensaje
		for (int i = 0; i < tamano1; i++) {
			if(!"".equals(mensaje1.get(i))) {
				posicionPrimeraPalabra1 = i;
				break;
			} 
		}
		
		for (int i = 0; i < tamano2; i++) {
			if(!"".equals(mensaje2.get(i))) {
				posicionPrimeraPalabra2 = i;
				break;
			} 
		}
		
		for (int i = 0; i < tamano3; i++) {
			if(!"".equals(mensaje3.get(i))) {
				posicionPrimeraPalabra3 = i;
				break;
			} 
		}
		
		//la primera aparicion de la primera palabra determina el tamanio del mensaje
		if(posicionPrimeraPalabra1<= posicionPrimeraPalabra2 && posicionPrimeraPalabra1<= posicionPrimeraPalabra3) {
			tamanoMensaje = tamano1-posicionPrimeraPalabra1;
		} else if(posicionPrimeraPalabra2<= posicionPrimeraPalabra1 && posicionPrimeraPalabra2<= posicionPrimeraPalabra3) {
			tamanoMensaje = tamano2-posicionPrimeraPalabra2;
		} else {
			tamanoMensaje = tamano3-posicionPrimeraPalabra3;
		}
		
		//inicio del mensaje para cada cadena
		int i = tamano1- tamanoMensaje;
		int j = tamano2- tamanoMensaje;
		int k = tamano3- tamanoMensaje;
		
		StringBuffer mensajeFinal= new StringBuffer();
		
		for (; i < tamano1; i++,j++,k++) {
			if(!"".equals(mensaje1.get(i))) {
				mensajeFinal.append(" " +mensaje1.get(i));
			} else if(!"".equals(mensaje2.get(j))) {
				mensajeFinal.append(" " +mensaje2.get(j));
			} else {
				mensajeFinal.append(" " +mensaje3.get(k));
			}
		}
		
		mensajeFinal.deleteCharAt(0);
		
		return mensajeFinal.toString();
	}
}
