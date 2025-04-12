/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hamza
 */
@Entity
@Table(name = "Medecins")
public class Medecin extends User {

    private String specialite;

    @OneToMany(mappedBy = "medecin")
    private List<Rdv> rdvs;

    public Medecin() {
    }

    public Medecin(String nom, String prenom, String email, String password, String specialite, List<Rdv> rdvs) {
        super(nom, prenom, email, password);
        this.specialite = specialite;
        this.rdvs = rdvs;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(List<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

}
