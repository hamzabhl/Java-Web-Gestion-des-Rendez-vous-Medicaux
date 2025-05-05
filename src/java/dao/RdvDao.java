package dao;

import entities.Rdv;
import java.sql.Date;
import java.sql.Time;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

public class RdvDao extends AbstractDao<Rdv> {

    public RdvDao() {
        super(Rdv.class);
    }

    public boolean existsByDateHeure(int medecinId, Date date, Time time) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean exists = false;

        try {
            String hql = "FROM Rdv WHERE medecin_id = :medecinId AND date = :date AND heure = :heure";
            Query query = session.createQuery(hql);
            query.setParameter("medecinId", medecinId);
            query.setParameter("date", date);
            query.setParameter("heure", time);
            exists = !query.list().isEmpty();
        } finally {
            session.close();
        }

        return exists;
    }

    public void save(Rdv rdv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(rdv);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}