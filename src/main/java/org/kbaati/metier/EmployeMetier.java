package org.kbaati.metier;

import java.util.List;

import org.kbaati.entities.Employe;

public interface EmployeMetier {

	public Employe saveEmploye(Employe e);
	public List<Employe> listEmploye();
	
}
