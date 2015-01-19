package ni.gob.minsa.hsf.domain.ws;

import ni.gob.minsa.hsf.domain.CaractHigSanitarias;
import ni.gob.minsa.hsf.domain.Persona;

public class PersonChs {
	
	private Persona persona;
	private CaractHigSanitarias chs;
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public CaractHigSanitarias getChs() {
		return chs;
	}
	public void setChs(CaractHigSanitarias chs) {
		this.chs = chs;
	}

}
