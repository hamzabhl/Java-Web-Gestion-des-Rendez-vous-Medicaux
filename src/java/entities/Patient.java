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
@Table(name = "Patients")
public class Patient extends User {

    private String telephone;

    @OneToMany(mappedBy = "patient")
    private List<Rdv> rdvs;

    public Patient() {
    }

    public Patient(String telephone, List<Rdv> rdvs, String nom, String prenom, String email, String password, String role) {
        super(nom, prenom, email, password, role);
        this.telephone = telephone;
        this.rdvs = rdvs;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(List<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

}