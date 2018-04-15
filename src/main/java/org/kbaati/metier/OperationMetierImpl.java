package org.kbaati.metier;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.kbaati.dao.CompteRepository;
import org.kbaati.dao.EmployeRepository;
import org.kbaati.dao.OperationRepository;
import org.kbaati.entities.Compte;
import org.kbaati.entities.Employe;
import org.kbaati.entities.Operation;
import org.kbaati.entities.Retrait;
import org.kbaati.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationMetierImpl implements OperationMetier{
	
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private EmployeRepository employeRepository;
	

	@Override
	@Transactional
	public Boolean verser(String code, double montant, Long codeEmp) {
		Compte cp=compteRepository.findOne(code);
		Employe e=employeRepository.findOne(codeEmp);
		Operation o=new Versement();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		o.setEmploye(e);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()+montant);				
		return true;
	}

	@Override
	@Transactional
	public Boolean retirer(String code, double montant, Long codeEmp) {
		Compte cp=compteRepository.findOne(code);
		if (cp.getSolde()<montant) throw new RuntimeException("Solde insuffisant");
		Employe e=employeRepository.findOne(codeEmp);
		Operation o=new Retrait();
		o.setDateOperation(new Date());
		o.setMontant(montant);
		o.setCompte(cp);
		o.setEmploye(e);
		operationRepository.save(o);
		cp.setSolde(cp.getSolde()-montant);				
		return true;
	}

	@Override
	@Transactional
	public Boolean virement(String cpte1, String cpte2, double montant, Long codeEmp) {
		retirer(cpte1, montant, codeEmp);
		verser(cpte2, montant, codeEmp);
		return true;
	}

}
