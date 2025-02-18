package algstudent.s0;


import java.util.ArrayList;
import java.util.List;

public class JavaA2 {
	public void isPrime() {
		int n = 10000; 
		
		for(int i = 0; i < 8; i++) {
			long startTime = System.currentTimeMillis();
			List <Integer> listadoPrimos=listadoPrimos(n);
			long endTime = System.currentTimeMillis();
			
			long duration = endTime - startTime;
			System.out.println("n = %d, ***, time = %d , milliseconds ".formatted(n,duration));
			n*=2;
		}
		
	}
	
	private List<Integer> listadoPrimos(int n) {
		List <Integer> primes = new ArrayList<>();
		for(int i = 2; i <= n; i++) {
			if(primoA2(i)) {
				primes.add(i);
			}
		}
		return primes;
	}

	private boolean primoA2(int m) {
		for(int i = 2; i < m; i++) {
			if(m%i==0) {
				return false;
			}
		}
		return true;
	}
}
