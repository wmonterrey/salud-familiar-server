package ni.gob.minsa.comunitaria.hsf.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import ni.gob.minsa.comunitaria.hsf.domain.Divisionpolitica;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;


public class DownloadDivisionPoliticaTask extends DownloadTask {
	private String error = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private String mensaje = null;
	private List<Divisionpolitica> divpolitica = null;
	
	@Override
	protected String doInBackground(String... values) {

		url = values[0];
		username = values[1];
		password = values[2];
		mensaje = values[3];
		
		
		try {
			error = descargarDivPols(url, username, password);
		} catch (Exception e1) {
			e1.printStackTrace();
			return e1.getLocalizedMessage();
		}
		if (divpolitica != null){
				// open db and clean entries
				va.open();
				va.deleteAllDiv();
				// download and insert comms
				try {
					addDivPol(divpolitica);
				} catch (Exception e) {
					// Regresa error al insertar
					e.printStackTrace();
					return e.getLocalizedMessage();
				}
				// close db and stream
				va.close();
		}
		return error;
	}

	private void addDivPol(List<Divisionpolitica> divpol) throws Exception {

		int c = divpol.size();

		ListIterator<Divisionpolitica> iter = divpol.listIterator();
		
		while (iter.hasNext()){
			va.createDivPolitica(iter.next());
			publishProgress(mensaje, Integer.valueOf(iter.nextIndex()).toString(), Integer
					.valueOf(c).toString());
		}

	}
	
	// url, username, password
	protected String descargarDivPols(String url, String username, 
			String password) throws Exception {
		try {
			// The URL for making the GET request
			final String urlRequest = url + "/movil/nicaragua";

			// Set the Accept header for "application/json"
			HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
			HttpHeaders requestHeaders = new HttpHeaders();
			List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
			acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
			requestHeaders.setAccept(acceptableMediaTypes);
			requestHeaders.setAuthorization(authHeader);

			// Populate the headers in an HttpEntity object to use for the request
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

			// Create a new RestTemplate instance
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

			// Perform the HTTP GET request
			ResponseEntity<Divisionpolitica[]> responseEntity = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
					Divisionpolitica[].class);

			// convert the array to a list and return it
			divpolitica = Arrays.asList(responseEntity.getBody());
			return null;

		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			return e.getLocalizedMessage();
		}
	}
	
}
