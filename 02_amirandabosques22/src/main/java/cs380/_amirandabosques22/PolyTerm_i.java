package cs380._amirandabosques22;

public interface PolyTerm_i {
	double coeff = 0;
	int exp = 0;
	
	 void setCoefficient(double aVal);//sets the receiver’s coefficient
	 
	 void setExponent(int aVal);//sets the receiver’s exponent 
	 double getCoefficient();
	 int getExponent();
	 double value(double x);//answers the value of the term evaluated at the specified number (x).
	 /*For example, 3x^2 produces 3*4 = 12 if
	 x is given to be 2.*/
	 PolyTerm add(PolyTerm other);
	 PolyTerm multiply(PolyTerm other);
	 boolean isNegative();
	 boolean isZero();
	 String toString();
}
