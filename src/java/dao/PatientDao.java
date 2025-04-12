/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;

/**
 *
 * @author hamza
 */
public class PatientDao extends AbstractDao<Patient> {
    public PatientDao() {
        super(Patient.class);
    }
}