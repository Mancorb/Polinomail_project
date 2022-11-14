package cs380._amirandabosques22;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
class PolyTermTest {

	@Test
	void test_setCoefficient() {
		PolyTerm plt = new PolyTerm();
		plt.setCoefficient(2);
		assertEquals(2, plt.coeff);
	}

	@Test
	void test_setExponent() {
		PolyTerm plt = new PolyTerm();
		plt.setExponent(3);
		assertEquals(3, plt.exp);
	}
	@Test
	void test_getCoefficient() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=3;
		assertEquals(3, plt.getCoefficient());
	}
	@Test
	void test_getExponent() {
		PolyTerm plt = new PolyTerm();
		plt.exp=2;
		assertEquals(2, plt.getExponent());
	}
	@Test
	void test_value() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=2;
		plt.exp=2;
		assertEquals(8, plt.value(2));
	}
	@Test
	void test_add() {
		PolyTerm plt1 = new PolyTerm();
		plt1.coeff=2;
		plt1.exp=2;
		PolyTerm plt2 = new PolyTerm();
		plt2.coeff=3;
		plt2.exp=2;
		PolyTerm res =plt1.add(plt2);
		assertEquals(5, res.coeff);
		assertEquals(2, res.exp);
	}
	@Test
	void test_add_wrong_value() { 
	  PolyTerm plt2 = new PolyTerm();
	  PolyTerm plt1 = new PolyTerm();
	  plt1.coeff=2;
	  plt1.exp=2;
	  plt2.coeff=3;
	  plt2.exp=1;
	  assertNull(plt1.add(plt2));
	} 
	@Test
	void test_multiply() {
		PolyTerm plt2 = new PolyTerm();
		PolyTerm plt1 = new PolyTerm();
		plt1.coeff=2;
		plt1.exp=2;
		plt2.coeff=3;
		plt2.exp=1;
		PolyTerm res = plt1.multiply(plt2);
		assertEquals(6, res.getCoefficient());
		assertEquals(3, res.getExponent());
	}
	@Test
	void isNegative_True() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=-1;
		plt.exp=2;
		assertTrue(plt.isNegative());
	}
	void isNegative_False() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=1;
		plt.exp=2;
		assertFalse(plt.isNegative());
	}
	@Test
	void isZero_True() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=0;
		plt.exp=0;
		assertTrue(plt.isZero());
	}
	@Test
	void isZero_False() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=1;
		plt.exp=2;
		assertFalse(plt.isZero());
	}
	@Test
	void test_toString_normal() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=2;
		plt.exp=2;
		assertEquals("2.0X^2", plt.toString());
	}
	@Test
	void test_toString_noexponent() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=2;
		plt.exp=0;
		assertEquals("2.0", plt.toString());
	}
	@Test
	void test_toString_minimum_exponent() {
		PolyTerm plt = new PolyTerm();
		plt.coeff=2;
		plt.exp=1;
		assertEquals("2.0X", plt.toString());
	}
}
