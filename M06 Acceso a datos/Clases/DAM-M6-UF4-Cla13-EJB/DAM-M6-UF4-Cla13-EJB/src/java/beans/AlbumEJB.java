package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Stateless
public class AlbumEJB {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    public List findAll(){
        return emf.createEntityManager().createNamedQuery("Album.findAll").getResultList();
    }
}
