package ni.gob.minsa.hsf.service;

import javax.annotation.Resource;

import ni.gob.minsa.hsf.domain.Familia;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("familiaService")
@Transactional
public class FamiliaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	
	public void addFamilia(Familia familia) {
		Session session = sessionFactory.getCurrentSession();
		session.save(familia);
	}
}
