package cs380._amirandabosques22;
import java.lang.Math;

public class Polynomial implements Polynomial_i{
	
	

	protected PolyTerm[] myTerms;
	public static final int INIT_SIZE=5;
	public int counter = 0;
	//Constructor
	public Polynomial() {
		myTerms = new PolyTerm[INIT_SIZE];
	}

	public int size() {
		return  counter;
	}

	public int degree() {
		if (counter==0) {return -1;}
		
		int maxD = myTerms[0].getExponent();
		
		if (counter==1) {return maxD;}
		
		//go through each and find the biggest
		for (int i=0; i<this.counter; i++) {
			if(myTerms[i].getExponent()>maxD) maxD=myTerms[i].getExponent();
		}
		return maxD;
	}


	public void addTerm(PolyTerm aTerm) {
		if (counter==0) {
			this.myTerms[counter] = aTerm;
			counter++;
		}
		
		else {
			//if the list if full, expand it
			if(counter==this.myTerms.length) {
				ExpandList();
			}
			//find coincidences
			boolean found = false;//flag if there is a coincidence
			for (int i =0; i<counter; i++) {
				PolyTerm temp =this.myTerms[i];
				//add them up
				if (temp.getExponent()==aTerm.getExponent()) {
					found=true;
					double val = temp.getCoefficient()+ aTerm.getCoefficient();
					//delete if it's = 0
					if (val==0) {
						//if its at the end just replace with null
						if(i==counter-1) {
							this.myTerms[i]=null;
							counter --;
						}
						//if it's in the middle of the list
						else {
							int subcounter = i+1;
							//move all remaining elements back a space until there're no more elements or space left in the array
							while(subcounter<counter && subcounter<this.myTerms.length) {
								this.myTerms[i]=this.myTerms[subcounter];
								this.myTerms[subcounter]=null;
								subcounter++;
							}
						}
					}
					else {
						//replace previous coefficient with the new value
						this.myTerms[i].setCoefficient(val);
						return;
					}
				}
			}
			//order the list
			if(!found) {
				//if its bigger than the first element move everything 
				if(aTerm.getExponent()>this.myTerms[0].getExponent()) {
					moveItems(0);
					//put the value in the first spot
					this.myTerms[0]=aTerm;
					counter++;
				}
				//look for it's proper place
				else {
					//find where to start
					int i =0;
					while(this.myTerms[i].getExponent()>aTerm.getExponent()) {
						i++;
						if (i==counter) {
							myTerms[counter]=aTerm;
							counter ++;
							return;
						}
					}
					
					//move everything else
					moveItems(i);
		
					//replace value
					this.myTerms[i]=aTerm;
					this.counter++;
				}
			}
		}
		
	}
	
	public void addTerm(double coef, int exp) {
		PolyTerm term = new PolyTerm();
		term.coeff=coef;
		term.exp=exp;
		addTerm(term);
	}
	
	protected void ExpandList() {
		//doubles the size of the original array to store more data
		PolyTerm [] temp = this.myTerms;
		myTerms = new PolyTerm[(this.myTerms.length*2)];
		int i =0;
		while(i!=temp.length) {
			this.myTerms[i]=temp[i];
			i++;
		}	
	}

	protected void moveItems(int start) {
		for(int i = counter; i>start; i--) {
			this.myTerms[i]=this.myTerms[i-1];
		}
	}
	
	public PolyTerm removeTerm(int anExp) {
		int i = indexOf(anExp);
		if (i!=-1) {
			PolyTerm result = myTerms[i];
			myTerms[i]=null;
			while(i<counter) {
				myTerms[i]=myTerms[i+1];
				myTerms[i+1]=null;
				i++;
			}
			counter--;
			return result;
		}
		
		return null;
	}

	public PolyTerm termAt(int pos) {
		pos = indexOf(pos);
		if (pos!=-1) {
			return myTerms[pos];
		}
		else {
			return null;
		}
	}

	public int indexOf(int anExp) {
		for(int i =0; i<counter; i++) {
			if(this.myTerms[i].getExponent()==anExp) {
				return i;
			}
		}
		return -1;
	}

	public double value(double x) {
		double res =0;
		for(int i=0; i<counter; i++) {
			res = res + (myTerms[i].getCoefficient()*
					Math.pow(x,myTerms[i].getExponent()));
		}
		return res;
	}

	public void addPolynomial(Polynomial other) {
		//element in other polynomial list
		for (int i =0; i<other.counter; i++) {
			boolean found =false;
			//element in this polynomial
			for(int j =0; j<this.counter; j++) {
				
				
				if (other.myTerms[i].getExponent()==this.myTerms[j].getExponent()) {
					
					PolyTerm temp= this.myTerms[j].add(other.myTerms[i]);
					
					
					if (temp.getCoefficient()==0) {
						removeTerm(myTerms[j].getExponent());
					}
					else {
						this.myTerms[j]=temp;
					}
					found=true;
					break;
				}
			}
			if(!found) {
				addTerm(other.myTerms[i]);
			}
		}
		
	}

	public void multPolynomial(Polynomial other) {
		Polynomial results[] = new Polynomial[other.size()];
		for (int i=0; i<results.length; i++) {
			Polynomial ply_ = new Polynomial();
			results[i]=ply_;
		}
		//go through the other object's list
		for (int i =0; i<other.counter;i++) {
			for(int j =0;j<this.counter; j++) {
				PolyTerm temp = myTerms[j].multiply(other.myTerms[i]);
				
				if (temp.getCoefficient()!=0) {
					//myTerms[j]=temp;
					results[i].addTerm(temp);
				}
				else {
					removeTerm(myTerms[i].getExponent());
				}
			}
		}
		if (results.length>1) {
			for(int i=1; i<results.length; i++) {
				results[0].addPolynomial(results[i]);
			}
		}
		this.myTerms=results[0].myTerms;
	}

	public String toString(double x) {
		String result = "";
		double finalTemp =0;
		double temp = 0;
		for (int i=0;i<counter;i++) {
			temp = this.myTerms[i].getCoefficient() * Math.pow(x, this.myTerms[i].getExponent());
			finalTemp += temp;
			result += String.valueOf(temp);
			if(i+1!=counter) {
				result +=" + ";
			}
			else {
				result +=" = ";
			}
		}
		result+= String.valueOf(finalTemp);
		return result;
	}
	
	public String toString() {
		String result = "";
		for (int i=0;i<counter;i++) {
			if(this.myTerms[i].getExponent()>0) {
				result += String.valueOf(myTerms[i].getCoefficient())+"X^"+String.valueOf(myTerms[i].getExponent());
			}
			else if(this.myTerms[i].getExponent()>0) {
				result += String.valueOf(myTerms[i].getCoefficient());
			}
			if(i+1!=counter) {
				if(this.myTerms[i+1].getCoefficient()>0) {
					result +=" + ";
				}
				else {
					result+=" ";
				}
			}
		}
		return result;
	}
	

}
