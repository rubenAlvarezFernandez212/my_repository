package algstudent.s11;

public class Vector6 {
static int[]v;
static int[]w;
	
	public static void main(String arg []) {
		int repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		int matches1 = 0;
		
		for (long n = 10_000; n <= 81_920_000; n *= 2){ 
			  v = new int[(int)n];
			  Vector1.fillIn(v);
			  w = new int[(int)n];
			  Vector1.fillIn(w);
			  
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     matches1 = Vector1.matches1(v,w);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%d milliseconds SUM=%d NTIMES=%d\n", n, t2-t1, matches1, repetitions);	
		}//for 
		
	}//
}
