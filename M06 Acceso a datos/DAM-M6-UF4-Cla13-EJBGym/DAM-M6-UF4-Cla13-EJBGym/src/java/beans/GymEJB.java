package beans;

import clasesPOJO.Actividad;
import clasesPOJO.Matricula;
import clasesPOJO.Socio;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Stateless
public class GymEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public List findAllActividades() {
        return emf.createEntityManager().createNamedQuery("Actividad.findAll").getResultList();
    }

    public Actividad findActividadByName(String nombre) {
        Query q = emf.createEntityManager().createNamedQuery("Actividad.findByNombre");
        q.setParameter("nombre", nombre);
        List<Actividad> result = q.getResultList();
        Iterator iter = result.iterator();
        Actividad a = (Actividad) iter.next();
        return a;
    }

    public List findAllSocios() {
        return emf.createEntityManager().createNamedQuery("Socio.findAll").getResultList();
    }

    public Socio findSocioByNumber(int numSocio) {
        Query q = emf.createEntityManager().createNamedQuery("Socio.findByNumsocio");
        q.setParameter("numsocio", numSocio);
        List<Socio> result = q.getResultList();
        Iterator iter = result.iterator();
        Socio a = (Socio) iter.next();
        return a;
    }

    public List findAllMatriculas() {
        return emf.createEntityManager().createNamedQuery("Matricula.findAll").getResultList();
    }

    public List findMatriculaBySocio(int numSocio) {
        Socio s = findSocioByNumber(numSocio);
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Matricula m WHERE m.socio = :socio");
        q.setParameter("socio", s);
        List matriculas = q.getResultList();
        return matriculas;
    }
    
    public List findMatriculaByActividad(String nombre) {
        Actividad a = findActividadByName(nombre);
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Matricula m WHERE m.actividad = :actividad");
        q.setParameter("actividad", a);
        List matriculas = q.getResultList();
        return matriculas;
    }

    public boolean insertarActividad(Actividad a) {
        if (!existeActividad(a)) {
            EntityManager em = emf.createEntityManager();
            em.persist(a);
            //        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }

    public boolean updateActividad(Actividad a) {
        EntityManager em = emf.createEntityManager();
        Actividad aux = em.find(Actividad.class, a.getNombre());
        if (aux != null) {
            aux.setPrecio(a.getPrecio());
            aux.setHoras(a.getHoras());
            em.persist(aux);
            em.close();
            return true;
        }
        return false;
    }

    public boolean eliminarActividad(Actividad a) {
        EntityManager em = emf.createEntityManager();
        Actividad aux = em.find(Actividad.class, a.getNombre());
        if (aux != null) {
            List<Matricula> matriculas = findMatriculaByActividad(aux.getNombre());
            if (matriculas != null) {
                for (int i = 0; i < matriculas.size(); i++) {
                    Matricula m = matriculas.get(i);
                    eliminarMatricula(m);
                }
            }
            em.remove(aux);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existeActividad(Actividad a) {
        EntityManager em = emf.createEntityManager();
        Actividad encontrada = em.find(Actividad.class, a.getNombre());
        em.close();
        return encontrada != null;
    }

    public boolean insertarSocio(Socio s) {
        if (!existeSocio(s)) {
            EntityManager em = emf.createEntityManager();
            em.persist(s);
            //        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }

    public boolean updateSocio(Socio s) {
        EntityManager em = emf.createEntityManager();
        Socio aux = em.find(Socio.class, s.getNumsocio());
        if (aux != null) {
            aux.setNombre(s.getNombre());
            aux.setApellidos(s.getApellidos());
            em.persist(aux);
            em.close();
            return true;
        }
        return false;
    }

    public boolean eliminarSocio(Socio s) {
        EntityManager em = emf.createEntityManager();
        Socio aux = em.find(Socio.class, s.getNumsocio());
        if (aux != null) {
            List<Matricula> matriculas = findMatriculaBySocio(aux.getNumsocio());
            if (matriculas != null) {
                for (int i = 0; i < matriculas.size(); i++) {
                    Matricula m = matriculas.get(i);
                    eliminarMatricula(m);
                }
            }
            em.remove(aux);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existeSocio(Socio s) {
        EntityManager em = emf.createEntityManager();
        Socio encontrado = em.find(Socio.class, s.getNumsocio());
        em.close();
        return encontrado != null;
    }

    public boolean insertarMatricula(Matricula m) {
        if (!existeMatricula(m)) {
            EntityManager em = emf.createEntityManager();
            em.persist(m);
            //        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }

    public boolean updateMatricula(Matricula m) {
        EntityManager em = emf.createEntityManager();
        Matricula aux = em.find(Matricula.class, m.getIdmatricula());
        if (aux != null) {
            aux.setActividad(m.getActividad());
            aux.setSocio(m.getSocio());
            aux.setFecha(m.getFecha());
            em.persist(aux);
            em.close();
            return true;
        }
        return false;
    }

    public boolean eliminarMatricula(Matricula m) {
        EntityManager em = emf.createEntityManager();
        Matricula aux = em.find(Matricula.class, m.getIdmatricula());
        if (aux != null) {
            em.remove(aux);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existeMatricula(Matricula m) {
        EntityManager em = emf.createEntityManager();
        Matricula encontrada = em.find(Matricula.class, m.getIdmatricula());
        em.close();
        return encontrada != null;
    }
}
