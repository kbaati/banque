package org.kbaati.metier;

import org.kbaati.entities.Compte;

public interface CompteMetier {

	public Compte saveCompte(Compte cp);
	public Compte getCompte(String code);
	
}
