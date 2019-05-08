import java.util.*;
/*
 * @author munjal
 */
public class Quicksort implements Runnable {
    
    int [] data;
    int start, end;
    
    Quicksort(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void run() {
        quicksort(this.data, this.start, this.end);
    }
    
    public static void quicksort(int[] data, int start, int end) {
        if(end <= start) {
            return;
        }
        
        int s = partition(data, start, end);
        quicksort(data, start, s-1);
        quicksort(data, s+1, end);
    }
    
    public static int partition(int[] data, int start, int end) {
        if(start == end) {
            return start;
        }
        
        int mid = (start + end)/2;
        
        int pivot = data[mid];
        int s = start - 1;
        
        for(int i=start; i<end; i++) {
            if(data[i] <= pivot) {
                swap(data, ++s, i);
            }
        }
        swap(data, ++s, end);
        return s;
    }

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }    
    
    public static int[] randomList(int n, int k) {
        Random random = new Random();
        int[] data = new int[n];
        for(int i=0; i<data.length; i++) {
            data[i] = random.nextInt(k);
        }
        return data;
    }
    
    public static void oneProcessor(int[] data) {
        int temp[] = data;
        
        quicksort(temp, 0, temp.length - 1);
    }
    
    public static void twoProcessor(int[] data, int n) {
    
        int temp[] = data;
        
        //partition of data
        int a = partition(temp, 0, n-1);
        
        Thread t1 = new Thread(new Quicksort(temp, 0, a-1));
        Thread t2 = new Thread(new Quicksort(temp, a, temp.length-1));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        }
        catch(Exception x) {
            System.out.println(x);
        }
        
        for(int i=0; i<data.length; i++) {
            //System.out.println(data[i]);
        }
    }
    
    public static void fourProcessor(int[] data, int n) {
        
        int temp[] = data;
        
        //partition of data
        int a = partition(temp, 0, n-1);
        
        //partition of a
        int b = partition(temp, 0, a-1);
        int c = partition(temp, a, n-1);
        
        Thread t1 = new Thread(new Quicksort(temp, 0, b-1));
        Thread t2 = new Thread(new Quicksort(temp, b, a-1));
        Thread t3 = new Thread(new Quicksort(temp, a, c-1));
        Thread t4 = new Thread(new Quicksort(temp, c, temp.length-1));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }
        catch(Exception x) {
            System.out.println(x);
        }
        
        for(int i=0; i<data.length; i++) {
            //System.out.println(data[i]);
        }
        
    }
    
    public static void eightProcessor(int[] data, int n) {
        
        int temp[] = data;
        
        //partition of data
        int a = partition(temp, 0, n-1);
        
        //partition of a
        int b = partition(temp, 0, a-1);
        int c = partition(temp, a, n-1);
        
        //partition of b
        int d = partition(temp, 0, b-1);
        int e = partition(temp, b, a-1);
        
        //partition of c
        int f = partition(temp, a, c-1);
        int g = partition(temp, c, n-1);
        
        
        Thread t1 = new Thread(new Quicksort(temp, 0, d-1));
        Thread t2 = new Thread(new Quicksort(temp, d, b-1));
        Thread t3 = new Thread(new Quicksort(temp, b, e-1));
        Thread t4 = new Thread(new Quicksort(temp, e, a-1));
        Thread t5 = new Thread(new Quicksort(temp, a, f-1));
        Thread t6 = new Thread(new Quicksort(temp, f, c-1));
        Thread t7 = new Thread(new Quicksort(temp, c, g-1));
        Thread t8 = new Thread(new Quicksort(temp, g, temp.length-1));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
        }
        catch(Exception x) {
            System.out.println(x);
        }
        
        for(int i=0; i<data.length; i++) {
            //System.out.println(data[i]);
        }
    }
    
    public static void main(String[] args) {
        
        int n = 100000000;
        int[] data = randomList(n, n);
        
        long one = System.currentTimeMillis();
        oneProcessor(data);
        System.out.println("Array sorted using single processor in: " + (System.currentTimeMillis() - one) + " ms");
        
        long two = System.currentTimeMillis();
        twoProcessor(data, n);
        System.out.println("Array sorted using two processor in: " + (System.currentTimeMillis() - two) + " ms");
        
        long four = System.currentTimeMillis();
        fourProcessor(data, n);
        System.out.println("Array sorted using four processor in " + (System.currentTimeMillis() - four) + " ms");
        
        long eight = System.currentTimeMillis();
        eightProcessor(data, n);
        System.out.println("Array sorted using eight processor in " + (System.currentTimeMillis() - eight) + " ms");
        
        
        //For specific number of processors
        
        /*Scanner s = new Scanner(System.in);        
        System.out.println("How many processors you want 1 or 2 or 4 or 8? ");
        int processor = s.nextInt();
        
        if(processor == 1) {
            long start = System.currentTimeMillis();
            quicksort(data, n, data.length-1);
            System.out.println("Array sorted using one processor in: " + (System.currentTimeMillis() - start) + " ms");
        }
        else if (processor == 2) {
            long start = System.currentTimeMillis();
            twoProcessor(data, n);
            System.out.println("Array sorted using two processor in: " + (System.currentTimeMillis() - start) + " ms");
        }
        else if(processor == 4) {
            long start = System.currentTimeMillis();
            fourProcessor(data, n);
            System.out.println("Array sorted using four processor in " + (System.currentTimeMillis() - start) + " ms");  
        }
        else if(processor == 8) {
            long start = System.currentTimeMillis();
            eightProcessor(data, n);
            System.out.println("Array sorted using eight processor in " + (System.currentTimeMillis() - start) + " ms");   
        }
        else {
            System.out.println("Can choose from 1 or 2 or 4 or 8 processors only!");
        }*/
    }
    
}
