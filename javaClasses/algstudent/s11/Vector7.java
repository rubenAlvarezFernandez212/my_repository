package algstudent.s11;

public class Vector7 {
static int[]v;
static int[]w;	
	public static void main(String arg []) {
		int repetitions = Integer.parseInt(arg[0]);
		long t1,t2;
		int matches2 = 0;
		
		for (long n = 10_000; n <= 81_920_000; n *= 2){ //n is increased *5   
			  v = new int[(int)n];
			  Vector1.fillIn(v);
			  w = new int[(int)n];
			  Vector1.fillIn(w);
			  t1 = System.currentTimeMillis();
			  //We have to repeat the whole process to be measured
			  for (int repetition=1; repetition<=repetitions; repetition++){    	
			     matches2 = Vector1.matches2(v,w);
			  }
			  t2 = System.currentTimeMillis();
			  System.out.printf("SIZE=%d TIME=%d milliseconds SUM=%d NTIMES=%d\n", n, t2-t1, matches2, repetitions);	
		}//for 
		
	}
}
