package cs380._amirandabosques22;

/**
 * added today
 * 
 * @author mahiggs
 *
 */
public interface Polynomial_i {
	
	int size();//returns the number of terms in the polynomial
	int degree();//returns the degree of the polynomial; returns -1 if polynomial is empty
	
	void addTerm(PolyTerm aTerm);
	void addTerm(double coef, int exp);
	PolyTerm removeTerm(int anExp);
	PolyTerm termAt(int pos);
	int indexOf(int anExp);
	double value(double x);
	void addPolynomial(Polynomial other);
	void multPolynomial(Polynomial other);
	String toString();
	String toString(double x);
}
