package cs380._amirandabosques22;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntegrationTest {

	protected void add_objs(Polynomial ply,double[] coef,int[] exp) {
		for (int i =0; i<coef.length;i++) {
			PolyTerm obj = new PolyTerm();
			obj.setCoefficient(coef[i]);
			obj.setExponent(exp[i]);
			ply.addTerm(obj);
		}
	}
	
	@Test
	void test_Size_Empty() {
		Polynomial ply = new Polynomial();
		assertEquals(0,ply.size());
	}
	
	@Test
	void test_Size_With_Elements() {
		Polynomial ply = new Polynomial();
		double[] c = {2};
		int[] e = {2};
		add_objs(ply,c,e);
		assertEquals(1, ply.size());
	}
	
	@Test
	void test_Degree_With_Value() {
		Polynomial ply = new Polynomial();
		double[] c = {2};
		int[] e = {2};
		add_objs(ply,c,e);
		
		assertEquals(1, ply.size());
		assertEquals(2, ply.degree());
	}
	
	@Test
	void test_Degree_With_Empty_List() {
		Polynomial ply = new Polynomial();
		assertEquals(0, ply.size());
		assertEquals(-1, ply.degree());
	}
	
	@Test
	void test_Degree_With_Multiple_Values() {
		Polynomial ply = new Polynomial();
		double[] c = {3,2};
		int[] e = {2,3};
		add_objs(ply,c,e);
		assertEquals(2, ply.size());
		assertEquals(3, ply.degree());
	}
	
	@Test
	void test_addTerm_to_empty_list() {
		Polynomial ply = new Polynomial();
		PolyTerm plt = new PolyTerm();
		plt.setCoefficient(2);
		plt.setExponent(3);
		
		ply.addTerm(plt);
		assertEquals(1, ply.size());
		assertEquals(plt, ply.myTerms[0]);
	}
	
	@Test
	void test_addTerm_multiple_values() {
		Polynomial ply = new Polynomial();
		double[] c = {3,4};
		int[] e = {2,3};
		add_objs(ply,c,e);
		
		assertEquals(2, ply.size());
		assertEquals(c[1], ply.myTerms[0].getCoefficient());
		assertEquals(e[1], ply.myTerms[0].getExponent());
		assertEquals(c[0], ply.myTerms[1].getCoefficient());
		assertEquals(e[0], ply.myTerms[1].getExponent());
	}
	
	@Test
	void test_addTerm_delete() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3};
		int[] e = {2,2};
		add_objs(ply,c,e);
		
		assertEquals(1, ply.size());
		assertEquals(5, ply.myTerms[0].getCoefficient());
		assertEquals(2, ply.myTerms[0].getExponent());
	}
	
	@Test
	void test_addTerm_in_middle() {
		Polynomial ply = new Polynomial();
		double[] c = {3,1,2};
		int[] e = {3,1,2};
		add_objs(ply,c,e);
		
		assertEquals(3, ply.counter);
		assertEquals(2, ply.myTerms[1].getCoefficient());
	}
	
	@Test
	void test_addTerm_coef_exp() {
		Polynomial ply = new Polynomial();
		ply.addTerm(2, 2);//2X^2
		assertEquals(1, ply.counter);
		assertEquals(2, ply.myTerms[0].getCoefficient());
		assertEquals(2, ply.myTerms[0].getExponent());
	}
	
	@Test
	void test_removeTerm() {
		Polynomial ply = new Polynomial();
		PolyTerm plt = new PolyTerm();
		plt.setCoefficient(2);
		plt.setExponent(2);
		ply.addTerm(plt);
		
		assertEquals(plt, ply.removeTerm(2));
		assertEquals(0, ply.counter);
		
	}
	
	@Test
	void test_removeTerm_nonExisting() {
		Polynomial ply = new Polynomial();
		assertNull(ply.removeTerm(2));
		assertEquals(0, ply.counter);
	}
	
	@Test
	void test_removeTerm_middle_obj() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3,4};
		int[] e = {2,1,0};
		add_objs(ply,c,e);
		
		PolyTerm removed = ply.removeTerm(1);
		assertEquals(3, removed.getCoefficient());
		assertEquals(1, removed.getExponent());
		assertEquals(2, ply.counter);
		assertEquals(2, ply.myTerms[0].getCoefficient());
		assertEquals(4, ply.myTerms[1].getCoefficient());
	}
	
	@Test
	void test_termAt() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3,4};
		int[] e = {2,1,0};
		add_objs(ply,c,e);
		
		PolyTerm res = ply.termAt(2);
		assertEquals(2, res.getCoefficient());
		assertEquals(2, res.getExponent());
		assertEquals(3, ply.size());
	}
	
	@Test
	void test_termAt_wrongGuess() {
		Polynomial ply = new Polynomial();
		assertNull(ply.termAt(1));
		assertEquals(0, ply.counter);
	}
	
	@Test
	void test_indexOf() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3,4};
		int[] e = {2,1,0};
		add_objs(ply,c,e);
		
		assertEquals(0, ply.indexOf(2));
		assertEquals(3, ply.size());
	}
	
	@Test
	void test_indexOf_worng_guess() {
		Polynomial ply = new Polynomial();
		assertEquals(-1, ply.indexOf(2));
		assertEquals(0, ply.counter);
	}
	
	@Test
	void test_value_simple_sum() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3};
		int[] e = {2,1};
		add_objs(ply,c,e);
		//2x^2 + 3x
		//2(2)^2 + 3(2)= 14
		assertEquals(14, ply.value(2));
		assertEquals(2, ply.counter);
	}
	
	@Test
	void test_value_negative_sum() {
		Polynomial ply = new Polynomial();
		double[] c = {2,-1};
		int[] e = {2,3};
		add_objs(ply,c,e);
		//2x^2 + -x^3
		//2(2)^2 + (-1(2)^3)= 0
		assertEquals(0, ply.value(2));
		assertEquals(2, ply.counter);
	}
	
	@Test
	void test_addpolinomial() {
		Polynomial ply = new Polynomial();
		Polynomial ply2 = new Polynomial();
		
		double[] c = {-3,2};
		int[] e = {1,0};
		add_objs(ply,c,e);
		
		double[] c2 = {1,-2};
		int[] e2 = {1,0};
		add_objs(ply2,c2,e2);
		// (– 3x + 2 ) + (x – 2) = –2x 
		
		ply.addPolynomial(ply2);
		assertEquals(1, ply.counter);
		assertEquals(-2, ply.myTerms[0].getCoefficient());
		assertEquals(1, ply.myTerms[0].getExponent());

	}
	
	@Test
	void test_multPolynomial() {
		Polynomial ply = new Polynomial();
		Polynomial ply2 = new Polynomial();
		
		double[] c = {-3};
		int[] e = {1};
		add_objs(ply,c,e);
		
		double[] c2 = {2};
		int[] e2 = {1};
		add_objs(ply2,c2,e2);
		
		ply.multPolynomial(ply2);
		assertEquals(1, ply.counter);
		assertEquals(-6, ply.myTerms[0].getCoefficient());
		assertEquals(2, ply.myTerms[0].getExponent());
	}
	
	@Test
	void test_toString() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3};
		int[] e = {2,1};
		add_objs(ply,c,e);
		
		String res = ply.toString();
		assertEquals("2.0X^2 + 3.0X^1", res);
	}
	
	@Test
	void test_toString_with_value() {
		Polynomial ply = new Polynomial();
		double[] c = {2,3};
		int[] e = {2,1};
		add_objs(ply,c,e);
		
		String res = ply.toString(2.0);
		assertEquals("8.0 + 6.0 = 14.0", res);
	}

	@Test
	void test_firstCase() {
		Polynomial ply = new Polynomial();
		Polynomial ply2 = new Polynomial();
		
		double[] c = {2,-3,2};
		int[] e = {2,1,0};
		add_objs(ply,c,e);
		
		double[] c2 = {1,-2};
		int[] e2 = {1,0};
		add_objs(ply2,c2,e2);
		// (2x^2 -3x + 2 ) + (x – 2) = 2x^2 - 2x 
		
		ply.addPolynomial(ply2);
		String res =ply.toString();
		System.out.println("First Case: "+res);
		assertEquals("2.0X^2 -2.0X^1", res);
	}

	@Test
	void test_secondCase() {
		Polynomial ply = new Polynomial();
		Polynomial ply2 = new Polynomial();
		
		double[] c = {2,-3,2};
		int[] e = {2,1,0};
		add_objs(ply,c,e);
		
		double[] c2 = {1,-2};
		int[] e2 = {1,0};
		add_objs(ply2,c2,e2);
		
		ply.multPolynomial(ply2);
		String res = ply.toString();
		System.out.println("Second Case: "+res);
		assertEquals("2.0X^3 -7.0X^2 + 8.0X^1", res);
	}
}
