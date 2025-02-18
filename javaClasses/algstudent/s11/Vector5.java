package algstudent.s11;

public class Vector5 {
    static int[] v;

    public static void main(String arg[]) {
        int repetitions = Integer.parseInt(arg[0]);
        long t1, t2;
        int[] maximum = new int[2]; // Array to store position and max value

        for (long n = 10_000; n <= 81_920_000; n *= 2) { // n is increased *5
            v = new int[(int)n];
            Vector1.fillIn(v);

            t1 = System.currentTimeMillis();
            // We have to repeat the whole process to be measured
            for (int repetition = 1; repetition <= repetitions; repetition++) {
                Vector1.maximum(v, maximum);
            }
            t2 = System.currentTimeMillis();

            System.out.printf("SIZE=%d TIME=%d milliseconds MAX_POSITION=%d MAX_VALUE=%d NTIMES=%d\n", 
                              n, t2 - t1, maximum[0], maximum[1], repetitions);
        } // for
    }
}

