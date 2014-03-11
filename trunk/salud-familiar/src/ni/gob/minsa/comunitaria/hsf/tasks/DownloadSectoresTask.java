package ni.gob.minsa.comunitaria.hsf.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import ni.gob.minsa.comunitaria.hsf.domain.Sector;

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


public class DownloadSectoresTask extends DownloadTask {
	private String error = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private String municipio = null;
	private String mensaje = null;
	private List<Sector> sectores = null;
	
	@Override
	protected String doInBackground(String... values) {

		url = values[0];
		username = values[1];
		password = values[2];
		municipio = values[3];
		mensaje = values[4];
		
		try {
			error = descargarSectores();
		} catch (Exception e) {
			// Regresa error al descargar
			e.printStackTrace();
			return e.getLocalizedMessage();
		}
			
		if (sectores != null){
				// open db and clean entries
				va.open();
				va.deleteAllSectores();
				// download and insert comms
				try {
					addSectores(sectores);
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

	private void addSectores(List<Sector> sectores) throws Exception {

		int c = sectores.size();

		ListIterator<Sector> iter = sectores.listIterator();
		
		while (iter.hasNext()){
			va.createSector(iter.next());
			publishProgress(mensaje, Integer.valueOf(iter.nextIndex()).toString(), Integer
					.valueOf(c).toString());
		}

	}
	
	// url, username, password
		protected String descargarSectores() throws Exception {
			try {
				// The URL for making the GET request
				final String urlRequest = url + "/movil/sectores/{municipio}";

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
				ResponseEntity<Sector[]> responseEntity = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity,
						Sector[].class, municipio);

				// convert the array to a list and return it
				sectores = Arrays.asList(responseEntity.getBody());
				return null;

			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
				return e.getLocalizedMessage();	
			}
		}

	
}
