import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class randomMatrixGenerator{

     public static void main(String []args)  throws FileNotFoundException{
     	Scanner sc = new Scanner(System.in);
     	System.out.println("How large of a system?");
     	int size = sc.nextInt();
     	System.out.println("How large of a range should the random numbers encapsulate?");
     	int range = sc.nextInt();
     	PrintStream o = new PrintStream(new File("matrix.txt"));
     	PrintStream console = System.out;
     	System.setOut(o);
        Random rand = new Random();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int n = rand.nextInt(range) - range / 2;
                System.out.printf("%d ", n);
            }
            System.out.println();
        }
        
        PrintStream p = new PrintStream(new File("rhs.txt"));
        System.setOut(p);
       
        for (int i = 0; i < size; i++) {
        	int n = rand.nextInt(range) - range / 2;
        	System.out.printf("%d ", n);
        	
        }
     }
}