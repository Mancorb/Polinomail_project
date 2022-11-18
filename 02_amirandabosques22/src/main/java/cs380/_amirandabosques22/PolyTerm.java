package cs380._amirandabosques22;
import java.lang.Math;

class InvalidExponentException extends Exception {
    
	private static final long serialVersionUID = 1L;

	public InvalidExponentException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}


	public class PolyTerm implements PolyTerm_i{
		double coeff = 0;
		int exp = 0;

	public void setCoefficient(double aVal) {
		this.coeff=aVal;
		
		
	}

	public void setExponent(int aVal) {
		this.exp=aVal;
		
	}

	public double getCoefficient() {
		return this.coeff;
	}

	public int getExponent() {
		return this.exp;
	}

	public double value(double x) {
		double result=0;
		result= getCoefficient() * Math.pow(x, getExponent());
		return result;
	}

	public PolyTerm add(PolyTerm other) {
		PolyTerm res = new PolyTerm();
		try {
			if(this.getExponent()!=other.getExponent()) {
				throw new InvalidExponentException("!! Invalid Exponent !!");
			}else {
				double temp = this.getCoefficient()+other.getCoefficient();
				res.setCoefficient(temp);
				res.setExponent(other.getExponent());
				return res;
			}
		}catch(InvalidExponentException ex) {
			 System.out.println(ex.getMessage());
			 return null;
		}
		
	}

	public PolyTerm multiply(PolyTerm other) {
		PolyTerm res = new PolyTerm();
		res.setCoefficient(this.getCoefficient()*other.getCoefficient());
		res.setExponent(this.getExponent()+other.getExponent());
		return res;
	}

	public boolean isNegative() {
		if (this.getCoefficient()<0) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public boolean isZero() {
		if (this.getCoefficient()==0)
		return true;
		else 
		return false;
	}
	
	public String toString() {
		String result =String.valueOf(this.getCoefficient());
		if(this.getExponent()!=0) {
			result= result+"X";
			if(this.getExponent()>1) {
				result=result+"^"+this.getExponent();
			}
		} 
			
		return result;
	}
}
