package prime;

import java.util.ArrayList;
import java.util.Iterator;

public class PrimeRange {
	
	
	/**
	 * Returns a list of Prime Numbers between a specified range of numbers.
	 * The edge numbers are not included in the list.
	 * @param min the starting point number range
	 * @param max the ending point of the number range
	 * @param fin signifies the outermost recursive call
	 * @return List containing the prime numbers in the specified range
	 */	 
	public ArrayList<Long> computePrimesInRangeUnOptimized(long min, long max, boolean fin){
		
		ArrayList<Long> primesSublist = new ArrayList<Long>();
		ArrayList<Long> primes = new ArrayList<Long>();
		
		if(max == 2){
			//Lowest Possible Recursion Level, Add first prime to list
			primesSublist.add((long)2);
			return primesSublist;
		}		

		double sqrt = Math.sqrt(max);
		long ceil = (long)Math.ceil(sqrt);
		
		//By it's last recursion the following statement contains all primes
		//in the range (1, sqrt(max)), now the actual range must only be divided 
		//by this list and not all numbers.
		primesSublist.addAll(computePrimesInRangeUnOptimized((long)1, ceil, false));
		
		long i = Math.max(min, primesSublist.get(primesSublist.size()-1));
		i++;
				
		if(fin && (ceil > min)){
			//Add Already Computed Primes for final case
			//only useful if sqrt(max) > min
			Iterator<Long> it = primesSublist.iterator();
			while(it.hasNext()){
				long prime = it.next();
				if(prime>min && prime<max){
					primes.add(prime);
				}
			}
		}
		
		if (i%2 == 0){
			i++;
		}
		
		//Iterate Odd Numbers and check prime using above generated prime sublist
		outloop:
		for(; i<max; i+=2){
			Iterator<Long> it = primesSublist.iterator();	
			while(it.hasNext()){
				if(i%it.next() == 0){
					continue outloop;
				}
			}
			primes.add(i);			
		}
		
		if(!fin){
			primesSublist.addAll(primes);
			return primesSublist;
		}
		else{			
			return primes;
		}		
	}
	
	/**
	 * Same Function as computePrimesInRange, but using certain optimizations.
	 * Optimizations Performed
	 * 	- Removed Unecessary else conditions
	 * 	- Added Sqrt in loop, Can help in larger values, otherwise expense > benefit
	 *  - Loop Optimization for division, multiple in one iteration
	 */
	public ArrayList<Long> computePrimesInRangeOptimized(long min, long max, boolean fin){
		
		ArrayList<Long> primesSublist = new ArrayList<Long>();
		ArrayList<Long> primes = new ArrayList<Long>();
		
		if(max == 2){
			//Lowest Possible Recursion Level, Add first prime to list
			primesSublist.add((long)2);
			return primesSublist;
		}		

		double sqrt = Math.sqrt(max);
		long ceil = (long)Math.ceil(sqrt);
		
		//By it's last recursion the following statement contains all primes
		//in the range (1, sqrt(max)), now the actual range must only be divided 
		//by this list and not all numbers.
		primesSublist.addAll(computePrimesInRangeUnOptimized((long)1, ceil, false));
		
		long i = Math.max(min, primesSublist.get(primesSublist.size()-1));
		i++;
				
		if(fin && (ceil > min)){
			//Add Already Computed Primes for final case
			//only useful if sqrt(max) > min
			Iterator<Long> it = primesSublist.iterator();
			while(it.hasNext()){
				long prime = it.next();
				if(prime>min && prime<max){
					primes.add(prime);
				}
			}
		}
		
		if (i%2 == 0){
			i++;
		}
		
		long prime;
		long sqrt_lim;
		//Iterate Odd Numbers and check prime using above generated prime sublist
		outloop:
		for(; i<max; i+=2){
			
			//Another Optimization check sqrt for all,
			//but may backfire in certain cases
			sqrt_lim = (long)Math.ceil(Math.sqrt(i));
						
			//Club Iterations into one
			int size = primesSublist.size();
			if(size > 1 && (size%2==0)){
				long prime0;
				long prime1;
				for(int j=0; j<size; j+=2){
					prime0 = primesSublist.get(j);
					prime1 = primesSublist.get(j+1);
					if(prime0 > sqrt_lim){
						break;
					}
					if((i%prime0 == 0) || (i%prime1 == 0)){
						continue outloop;
					}
				}
				primes.add(i);				
			}
			//Normal Case
			else{
				Iterator<Long> it = primesSublist.iterator();
				while(it.hasNext()){
					prime = it.next();
					if(prime > sqrt_lim){
						break;
					}
					if(i%prime == 0){
						continue outloop;
					}
				}
				primes.add(i);
			}
		}
		
		if(!fin){
			primesSublist.addAll(primes);
			return primesSublist;
		}
		// Removed Extra Else
		return primes;	
	}
	
	/**
	 * Overloaded method to add default argument and validate arguments and choose version
	 * @param min Starting point of the range
	 * @param max Ending point of the range
	 * @param opt Run Optimized Version or not
	 */
	public ArrayList<Long> computePrimesInRange(long min, long max, boolean opt) 
			throws IllegalArgumentException{
		if((max - min < 2) ||(max < 1) || (min <1)){
			throw new IllegalArgumentException();
		}
		if(opt){
			return computePrimesInRangeOptimized(min, max, true);
		}
		return computePrimesInRangeUnOptimized(min,max, true);
	}
	
	/**
	 * Method to run a number of samples for the Prime Number Generator
	 * Measures time taken for execution of each individual run and gain by optimization
	 */
	public static void measure(long[] min, long[] max){
		PrimeRange pr = new PrimeRange();
		
		for(int i=0; i<min.length; i++){
			System.out.println("Measure Case "+ min[i] + "-" + max[i] + 
					" Unoptimized:-");
			long start = System.nanoTime();
			pr.computePrimesInRange(min[i], max[i], false);
			long end = System.nanoTime();
			long runtimeU = end - start;
			System.out.println("Runtime:" + runtimeU);
		
			System.out.println("Measure Case "+ min[i] + "-" + max[i] + 
					" Optimized:-");
			start = System.nanoTime();
			pr.computePrimesInRange(min[i], max[i], true);
			end = System.nanoTime();
			long runtimeO = end - start;
			System.out.println("Runtime:" + runtimeO);
			double gain = (runtimeU - runtimeO)*100/(runtimeU);
			System.out.println("Gain:" + gain + "%\n");
		}
	}
	
	public static void main(String[] args){
		if (args.length == 0){
			measure(new long[] {1, 10, 1000}, new long[] {100, 10000, 1000000});
		}
		else{
			PrimeRange pr = new PrimeRange();
			System.out.println(
					pr.computePrimesInRange(
							Long.parseLong(args[0]),
							Long.parseLong(args[1]),
							false));
		}
		
		
	}
	
}
