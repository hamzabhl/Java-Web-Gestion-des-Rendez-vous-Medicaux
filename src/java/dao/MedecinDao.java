/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medecin;
import java.util.List;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author hamza
 */
public class MedecinDao extends AbstractDao<Medecin> {

    public MedecinDao() {
        super(Medecin.class);
    }

    public List<Medecin> findBySpecialite(String specialite) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Medecin> result = session.createQuery("FROM Medecin WHERE specialite = :spec")
                .setParameter("spec", specialite)
                .list(); // or getResultList() if available
        session.close();
        return result;
    }
}