import java.util.*;
/*
 * @author munjal
 */
public class Bitonicsort implements Runnable {
    
    int [] data;
    int low, count, dir;
    
    Bitonicsort(int[] data, int low, int count, int dir) {
        this.data = data;
        this.low = low;
        this.count = count;
        this.dir = dir;
    }
    
    @Override
    public void run() {
        bitonicSort(this.data, this.low, this.count, this.dir);
    }
    
    public static void compAndSwap(int a[], int i, int j, int dir) {
        if((a[i] > a[j] && dir == 1) || a[i] < a[j] && dir == 0) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
    
    public static void bitonicMerge(int a[], int low, int count, int dir) {
        if(count > 1) {
            int k = count/2;
            
            for(int i=low; i<low+k; i++) {
                compAndSwap(a, i, i+k, dir);
            }
            
            bitonicMerge(a, low, k, dir);
            bitonicMerge(a, low+k, k, dir);
        }
    }
    
    public static void bitonicSort(int a[], int low, int count, int dir) {
        if(count > 1) {
            int k = count/2;
            
            bitonicSort(a, low, k, 1);
            
            bitonicSort(a, low+k, k, 0);
            
            bitonicMerge(a, low, count, dir);
        }
    }
    
    public static void sort(int a[], int N, int up) {
        bitonicSort(a, 0, N, up);
    }
    
    public static void printArray(int a[]) {
        int n = a.length;
        
        for(int i=0; i<n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    public static int[] randomList(int n, int k) {
        Random random = new Random();
        int[] data = new int[n];
        
        for(int i=0; i<data.length; i++) {
            data[i] = random.nextInt(k);
        }
        return data;
    }
    
    public static int half(int[] data, int start, int end) {
        int mid = (start + end) / 2;
        return mid;
    }
    
    public static void linear(int[] data, int length, int dir) {
        int temp[] = data;
        sort(temp, temp.length, dir);
    }
    
    public static void twoProcessor(int[] data, int length, int dir) {
        int temp[] = data;
        
        //partition of data
        int a = half(temp, 0, length);
        
        Thread t1 = new Thread(new Bitonicsort(temp, 0, a-1, dir));
        Thread t2 = new Thread(new Bitonicsort(temp, a, (temp.length-1)-a, dir));
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        }
        catch(Exception x) {
            System.out.println(x);
        }
    }
    
    public static void fourProcessor(int[] data, int length, int dir) {
        int temp[] = data;
        
        //partition of data
        int a = half(temp, 0, length);
        
        //partition of a
        int b = half(temp, 0, a-1);
        int c = half(temp, a, length);
        
        Thread t1 = new Thread(new Bitonicsort(temp, 0, b-1, dir));
        Thread t2 = new Thread(new Bitonicsort(temp, b, (a-1)-b, dir));
        Thread t3 = new Thread(new Bitonicsort(temp, a, (c-1)-a, dir));
        Thread t4 = new Thread(new Bitonicsort(temp, c, (temp.length-1)-c, dir));
        
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
    }
    
    public static void eightProcessor(int[] data, int length, int dir) {
        
        int temp[] = data;
        
        //partition of dataa
        int a = half(temp, 0, length);
        
        //partition of a
        int b = half(temp, 0, a-1);
        int c = half(temp, a, length);
        
        //partition of b
        int d = half(temp, 0, b-1);
        int e = half(temp, b, a-1);
        
        //partition of c
        int f = half(temp, a, c-1);
        int g = half(temp, c, length);
        
        Thread t1 = new Thread(new Bitonicsort(temp, 0, d-1, dir));
        Thread t2 = new Thread(new Bitonicsort(temp, d, (b-1)-d, dir));
        Thread t3 = new Thread(new Bitonicsort(temp, b, (e-1)-b, dir));
        Thread t4 = new Thread(new Bitonicsort(temp, e, (a-1)-e, dir));
        Thread t5 = new Thread(new Bitonicsort(temp, a, (f-1)-a, dir));
        Thread t6 = new Thread(new Bitonicsort(temp, f, (c-1)-f, dir));
        Thread t7 = new Thread(new Bitonicsort(temp, c, (g-1)-c, dir));
        Thread t8 = new Thread(new Bitonicsort(temp, g, (temp.length-1)-g, dir));
        
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
    }
    
    public static void main(String[] args) {
        
        int dir = 1;
        
        /*int a[] = {3,7,4,8,6,2,1,5};
        linear(a, a.length, dir);
        System.out.println("Sorted Array: ");
        printArray(a);*/
        
        int n = 100000000;
        int[] data = randomList(n, n);
        
        long linear = System.currentTimeMillis();
        linear(data, data.length, dir);
        System.out.println("Array sorted linearly in: " + (System.currentTimeMillis() - linear) + " ms");
        
        long two = System.currentTimeMillis();
        twoProcessor(data, data.length, dir);
        System.out.println("Array sorted using two processor in: " + (System.currentTimeMillis() - two) + " ms");
        
        long four = System.currentTimeMillis();
        fourProcessor(data, data.length, dir);
        System.out.println("Array sorted using four processors in: " + (System.currentTimeMillis() - four) + " ms");
        
        long eight = System.currentTimeMillis();
        eightProcessor(data, data.length, dir);
        System.out.println("Array sorted using eight processor in: " + (System.currentTimeMillis() - eight) + " ms");
        
        
        
        //For specific number of processors
        
        /*Scanner s = new Scanner(System.in);        
        System.out.println("How many processors you want 1 or 2 or 4 or 8? ");
        int processor = s.nextInt();
        
        if(processor == 1) {
            long start = System.currentTimeMillis();
            linear(data, data.length, dir);
            System.out.println("Array sorted using one processor in: " + (System.currentTimeMillis() - start) + " ms");
        }
        else if (processor == 2) {
            long start = System.currentTimeMillis();
            twoProcessor(data, data.length, dir);
            System.out.println("Array sorted using two processor in: " + (System.currentTimeMillis() - start) + " ms");
        }
        else if(processor == 4) {
            long start = System.currentTimeMillis();
            fourProcessor(data, data.length, dir);
            System.out.println("Array sorted using four processor in " + (System.currentTimeMillis() - start) + " ms");  
        }
        else if(processor == 8) {
            long start = System.currentTimeMillis();
            eightProcessor(data, data.length, dir);
            System.out.println("Array sorted using eight processor in " + (System.currentTimeMillis() - start) + " ms");   
        }
        else {
            System.out.println("Can choose from 1 or 2 or 4 or 8 processors only!");
        }*/
    }
    
}
