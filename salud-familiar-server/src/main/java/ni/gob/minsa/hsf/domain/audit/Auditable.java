package ni.gob.minsa.hsf.domain.audit;

public interface Auditable {
	
	public boolean isFieldAuditable(String fieldname);

}
