package org.kbaati.metier;

public interface OperationMetier {

	public Boolean verser(String code, double montant, Long codeEmp);
	public Boolean retirer(String code, double montant, Long codeEmp);
	public Boolean virement(String cpte1, String cpte2, double montant, Long codeEmp);
	public PageOperation getOperation(String codeCompte, int page, int size);

}
