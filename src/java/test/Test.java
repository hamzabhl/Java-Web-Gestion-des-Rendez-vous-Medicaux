/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.*;
import entities.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author hamza
 */
public class Test {
    public static void main(String[] args) {
        
        RdvDao rd = new RdvDao();
        MedecinDao md = new MedecinDao();
        PatientDao pd = new PatientDao();
        UserDao ud = new UserDao();

        //create Doctors 
        Medecin med1 = new Medecin("TAZI", "Mohamed", "tazi.med@gmail.com", ud.hashPassword("Random"), "Cardiologie", null);
        Medecin med2 = new Medecin("Rami", "Anwar", "rami.anwar@gmail.com", ud.hashPassword("Random"), "Dermatologie", null);
        Medecin med3 = new Medecin("DEHBI", "Karim", "dehbi.karim@gmail.com", ud.hashPassword("Random"), "Ophtalmologie", null);
        Medecin med4 = new Medecin("TAZI", "Fouad", "tazi.fouad@gmail.com", ud.hashPassword("Random"), "Anesthésiologie", null);
        md.create(med1);
        md.create(med2);
        md.create(med3);
        md.create(med4);

        //create Patients
        Patient pat1 = new Patient("SAAD", "imad", "rami.fouad@yahoo.com", ud.hashPassword("Random1"), "0623443561", null);
        Patient pat2 = new Patient("RAMI", "Noura", "rami.fouad@yahoo.com", ud.hashPassword("Random2"), "0775931234", null);
        Patient pat3 = new Patient("IDRISSI", "Amina", "rami.fouad@yahoo.com", ud.hashPassword("Random3"), "0623450684", null);
        Patient pat4 = new Patient("TIJANI", "Walid", "rami.fouad@yahoo.com", ud.hashPassword("Random4"), "0729503050", null);
        pd.create(pat1);
        pd.create(pat2);
        pd.create(pat3);
        pd.create(pat4);

        //create Rdv
        Rdv rdv1 = new Rdv(Date.valueOf("2025-07-20"), Time.valueOf("10:00:00"), med1, pat1);
        rd.create(rdv1);

        //filter by Doctors Specialty
        List<Medecin> list1 = md.findBySpecialite("Cardiologie");
        System.out.println("\nMedecins qui sont des Cardiologues ");
        for (Medecin m : list1) {
            System.out.println("Id : " + m.getId() + " ; " + m.getPrenom() + " " + m.getNom() + " ; " + m.getSpecialite());
        }

    }
}