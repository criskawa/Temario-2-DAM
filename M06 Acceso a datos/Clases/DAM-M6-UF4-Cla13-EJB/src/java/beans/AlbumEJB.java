package beans;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import java.util.List;

@Stateless
public class AlbumEJB {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    public List findAll(){
        return emf.createEntityManager().createNamedQuery("Album.findAll").getResultList();
    }
}
