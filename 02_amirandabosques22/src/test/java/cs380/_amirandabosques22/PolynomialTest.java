package cs380._amirandabosques22;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class PolynomialTest {

	private void addObjects(Polynomial obj, PolyTerm ...objs) {
		for (int i = 0; i< objs.length; i++) {
			obj.myTerms[obj.counter] = objs[i];
			obj.counter++;
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
		PolyTerm mpolT = mock(PolyTerm.class);
		ply.myTerms[0]= mpolT;
		ply.counter++;
		assertEquals(1, ply.size());
	}
	
	@Test
	void test_Degree_With_Value() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(3);
		
		addObjects(ply,mpolT);
		
		assertEquals(1, ply.size());
		assertEquals(3, ply.degree());
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
		PolyTerm mpolT = mock(PolyTerm.class);
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(3);
		when(mpolT2.getExponent()).thenReturn(4);
		
		addObjects(ply,mpolT,mpolT2);
		
		assertEquals(2, ply.size());
		assertEquals(4, ply.degree());
	}
	
	@Test
	void test_addTerm_to_empty_list() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		//2x^2
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn((double) 2);
		
		ply.addTerm(mpolT);
		assertEquals(1, ply.size());
		assertEquals(mpolT, ply.myTerms[0]);
	}
	
	@Test
	void test_addTerm_multiple_values() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);//X^2
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn((double) 1).thenReturn(3.0);
		PolyTerm mpolT2 = mock(PolyTerm.class);//2X^2
		when(mpolT2.getExponent()).thenReturn(2);
		when(mpolT2.getCoefficient()).thenReturn((double) 2);
		
		addObjects(ply, mpolT);
		ply.addTerm(mpolT2);
		
		assertEquals(1, ply.size());
		assertEquals(3, ply.myTerms[0].getCoefficient());
		assertEquals(2, ply.myTerms[0].getExponent());
	}
	
	@Test
	void test_addTerm_delete() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);//X^2
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn((double) 1);
		PolyTerm mpolT2 = mock(PolyTerm.class);//-X^2
		when(mpolT2.getExponent()).thenReturn(2);
		when(mpolT2.getCoefficient()).thenReturn((double) -1);
		
		addObjects(ply, mpolT);
		ply.addTerm(mpolT2);
		
		assertEquals(0, ply.counter);
	}
	
	@Test
	void test_addTerm_in_middle() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);//5X^2
		when(mpolT.getExponent()).thenReturn(3);
		when(mpolT.getCoefficient()).thenReturn((double) 5);
		PolyTerm mpolT2 = mock(PolyTerm.class);//2X
		when(mpolT2.getExponent()).thenReturn(1);
		when(mpolT2.getCoefficient()).thenReturn((double) 2);
		PolyTerm mpolT3 = mock(PolyTerm.class);//3X
		when(mpolT3.getExponent()).thenReturn(2);
		when(mpolT3.getCoefficient()).thenReturn((double) 3);
		
		addObjects(ply, mpolT,mpolT2);
		
		ply.addTerm(mpolT3);
		
		assertEquals(3, ply.counter);
		assertEquals(3, ply.myTerms[1].getCoefficient());
	}

	@Test
	void test_addTerm_coef_exp() {
		Polynomial ply = new Polynomial();
		ply.addTerm(2, 2);//2X^2
		assertEquals(1, ply.counter);
		assertEquals(2, ply.myTerms[0].coeff);
		assertEquals(2, ply.myTerms[0].exp);
	}
	
	@Test
	void test_removeTerm() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		addObjects(ply, mpolT);
		assertEquals(mpolT, ply.removeTerm(2));
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
		PolyTerm mpolT = mock(PolyTerm.class);
		PolyTerm mpolT2 = mock(PolyTerm.class);
		PolyTerm mpolT3 = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		when(mpolT2.getExponent()).thenReturn(3);
		when(mpolT2.getCoefficient()).thenReturn(3.0);
		when(mpolT3.getExponent()).thenReturn(1);
		when(mpolT3.getCoefficient()).thenReturn(1.0);
		addObjects(ply, mpolT,mpolT2,mpolT3);
		
		assertEquals(mpolT, ply.removeTerm(2));
		assertEquals(2, ply.counter);
		assertEquals(3, ply.myTerms[0].getCoefficient());
		assertEquals(1, ply.myTerms[1].getCoefficient());
	}
	
	@Test
	void test_termAt() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		addObjects(ply, mpolT);
		
		assertEquals(mpolT, ply.termAt(2));
		assertEquals(1, ply.counter);
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
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		addObjects(ply, mpolT);
		
		assertEquals(0, ply.indexOf(2));
		assertEquals(1, ply.counter);
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
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT2.getExponent()).thenReturn(1);
		when(mpolT2.getCoefficient()).thenReturn(3.0);
		addObjects(ply, mpolT,mpolT2);
		//2x^2 + 3x
		//2(2)^2 + 3(2)= 14
		assertEquals(14, ply.value(2));
		assertEquals(2, ply.counter);
	}
	
	@Test
	void test_value_negative_sum() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT2.getExponent()).thenReturn(3);
		when(mpolT2.getCoefficient()).thenReturn(-1.0);
		addObjects(ply, mpolT,mpolT2);
		//2x^2 + -x^3
		//2(2)^2 + (-1(2)^3)= 0
		assertEquals(0, ply.value(2));
		assertEquals(2, ply.counter);
	}

	@Test
	void test_addpolinomial() {
		Polynomial ply = new Polynomial();
		Polynomial ply2 = new Polynomial();
		
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(0);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT2.getExponent()).thenReturn(1);
		when(mpolT2.getCoefficient()).thenReturn(-3.0);
		
		
		PolyTerm mpolT3 = mock(PolyTerm.class);
		when(mpolT3.getExponent()).thenReturn(1);
		when(mpolT3.getCoefficient()).thenReturn(1.0);
		
		PolyTerm mpolT4 = mock(PolyTerm.class);
		when(mpolT4.getExponent()).thenReturn(0);
		when(mpolT4.getCoefficient()).thenReturn(-2.0);
		
		
		PolyTerm mpolTRes_1 = mock(PolyTerm.class);
		when(mpolTRes_1.getExponent()).thenReturn(1);
		when(mpolTRes_1.getCoefficient()).thenReturn(-2.0);
		
		
		
		PolyTerm mpolTRes_2 = mock(PolyTerm.class);
		when(mpolTRes_2.getExponent()).thenReturn(0);
		when(mpolTRes_2.getCoefficient()).thenReturn(0.0);
		
		when(mpolT2.add(mpolT3)).thenReturn(mpolTRes_1);
		when(mpolT.add(mpolT4)).thenReturn(mpolTRes_2);
		
		addObjects(ply, mpolT,mpolT2);
		addObjects(ply2, mpolT3,mpolT4);
		// (– 3x + 2 ) + (x – 2) = –2x 
		
		ply.addPolynomial(ply2);
		assertEquals(1, ply.counter);
		assertEquals(-2, ply.myTerms[0].getCoefficient(),ply.myTerms[0].getExponent());

	}

	@Test
	void test_multPolynomial() {
		Polynomial ply = new Polynomial();
		Polynomial ply2 = new Polynomial();
		
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(1);
		when(mpolT.getCoefficient()).thenReturn(-3.0);
		
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT2.getExponent()).thenReturn(1);
		when(mpolT2.getCoefficient()).thenReturn(2.0);
		
		
		PolyTerm mpolTRes_1 = mock(PolyTerm.class);
		when(mpolTRes_1.getExponent()).thenReturn(2);
		when(mpolTRes_1.getCoefficient()).thenReturn(-6.0);
		
		when(mpolT.multiply(mpolT2)).thenReturn(mpolTRes_1);
		
		addObjects(ply, mpolT);
		addObjects(ply2, mpolT2);
		
		ply.multPolynomial(ply2);
		assertEquals(1, ply.counter);
		assertEquals(-6, ply.myTerms[0].getCoefficient());
		assertEquals(2, ply.myTerms[0].getExponent());
	}

	@Test
	void test_toString() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT2.getExponent()).thenReturn(1);
		when(mpolT2.getCoefficient()).thenReturn(3.0);
		addObjects(ply, mpolT,mpolT2);
		
		String res = ply.toString();
		assertEquals("2.0X^2 + 3.0X^1", res);
	}
	
	@Test
	void test_toString_with_value() {
		Polynomial ply = new Polynomial();
		PolyTerm mpolT = mock(PolyTerm.class);
		when(mpolT.getExponent()).thenReturn(2);
		when(mpolT.getCoefficient()).thenReturn(2.0);
		PolyTerm mpolT2 = mock(PolyTerm.class);
		when(mpolT2.getExponent()).thenReturn(1);
		when(mpolT2.getCoefficient()).thenReturn(3.0);
		addObjects(ply, mpolT,mpolT2);
		
		String res = ply.toString(2.0);
		assertEquals("8.0 + 6.0 = 14.0", res);
	}
}
