package dao;

import entities.Medecin;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class MedecinDao extends AbstractDao<Medecin> {

    public MedecinDao() {
        super(Medecin.class);
    }

    public List<Medecin> searchByNameOrSpecialite(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Medecin> result = null;

        try {
            String hql = "FROM Medecin WHERE LOWER(nom) LIKE :kw OR LOWER(specialite) LIKE :kw";
            Query query = session.createQuery(hql);
            query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
            result = query.list();
        } finally {
            session.close();
        }

        return result;
    }

}