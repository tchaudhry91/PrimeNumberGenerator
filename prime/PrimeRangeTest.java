package prime;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PrimeRangeTest {
	
	PrimeRange pr;
	ArrayList<Long> sample_1_10;
	ArrayList<Long> sample_5_100;
	ArrayList<Long> sample_1000_1002;
	
	int count1_100;
	int count10_10000;
	int count10000_1000000;
	
	public PrimeRangeTest(){
		pr = new PrimeRange();		
		
		sample_1_10 = new ArrayList<Long>();
		sample_1_10.add((long)2);
		sample_1_10.add((long)3);
		sample_1_10.add((long)5);
		sample_1_10.add((long)7);
		
		sample_5_100 = new ArrayList<Long>();
		sample_5_100.add((long)7);
		sample_5_100.add((long)11);
		sample_5_100.add((long)13);
		sample_5_100.add((long)17);
		sample_5_100.add((long)19);
		sample_5_100.add((long)23);
		sample_5_100.add((long)29);
		sample_5_100.add((long)31);
		sample_5_100.add((long)37);
		sample_5_100.add((long)41);
		sample_5_100.add((long)43);
		sample_5_100.add((long)47);
		sample_5_100.add((long)53);
		sample_5_100.add((long)59);
		sample_5_100.add((long)61);
		sample_5_100.add((long)67);
		sample_5_100.add((long)71);
		sample_5_100.add((long)73);
		sample_5_100.add((long)79);
		sample_5_100.add((long)83);
		sample_5_100.add((long)89);
		sample_5_100.add((long)97);
		
		sample_1000_1002 = new ArrayList<Long>();
		
		count1_100 = 25;
		count10_10000 = 1225;
		count10000_1000000 = 77269;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEmptyList(){
		pr.computePrimesInRange(1, 2, false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegetiveArgument(){
		pr.computePrimesInRange(-10, 2, false);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testZeroArgument(){
		pr.computePrimesInRange(0, 10, false);
	}
	
	@Test
	public void testSample1To10(){
		ArrayList<Long> out_primes = pr.computePrimesInRange(1, 10, true);
		assertTrue(out_primes.equals(sample_1_10));
		out_primes = pr.computePrimesInRange(1, 10, false);
		assertTrue(out_primes.equals(sample_1_10));
	}
	
	@Test
	public void testSample5To100(){
		ArrayList<Long> out_primes = pr.computePrimesInRange(5, 100, false);
		assertTrue(out_primes.equals(sample_5_100));
		out_primes = pr.computePrimesInRange(5, 100, true);
		assertTrue(out_primes.equals(sample_5_100));
	}
	
	
	@Test
	public void testSample1000To1002(){
		//Empty List
		ArrayList<Long> out_primes = pr.computePrimesInRange(1000, 1002, false);
		assertTrue(out_primes.equals(sample_1000_1002));
		out_primes = pr.computePrimesInRange(1000, 1002, true);
		assertTrue(out_primes.equals(sample_1000_1002));
	}
	
	@Test
	public void testCount1to100(){
		ArrayList<Long> out_primes = pr.computePrimesInRange(1, 100, false);
		assertTrue(out_primes.size()==count1_100);
		out_primes = pr.computePrimesInRange(1, 100, true);
		assertTrue(out_primes.size()==count1_100);
	}
	
	@Test
	public void testCount10to10000(){
		ArrayList<Long> out_primes = pr.computePrimesInRange(10, 10000, false);
		assertTrue(out_primes.size()==count10_10000);
		out_primes = pr.computePrimesInRange(10, 10000, true);
		assertTrue(out_primes.size()==count10_10000);
	}
	
	@Test
	public void testCount10000to1000000(){
		ArrayList<Long> out_primes = pr.computePrimesInRange(10000, 1000000, false);
		assertTrue(out_primes.size()==count10000_1000000);
		out_primes = pr.computePrimesInRange(10000, 1000000, true);
		assertTrue(out_primes.size()==count10000_1000000);
	}
}
