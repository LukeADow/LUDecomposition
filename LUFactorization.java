import java.util.Scanner;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
/*

 © Luke Dow, 2019

 This program uses LU Decomposition to solve a square system of linear equations
 of any size

 to solve the system AX = Y, where A is an n x n matrix and X and Y are n x 1 vectors,
 we use the equations:

 A = LU
 LW = Y
 UX = W

 where X is the solution vector

 This program currently takes in input from two files, with spaces in between
 numbers (make sure to change to your own directory). The program automatically
 pads the String with space characters to help in parsing the doubles from the files.
 The program also currently outputs to a File named "LUFactorization.txt", however,
 this can easily be fixed by commenting out the PrintStream object lines.

 The current implementation only works for systems with a unique solution. Systems
 with an infinite solution set or systems with no solution will show up either
 with "NaN" or "Infinity" in the output file. There is currently no method
 to check whether the system has an infinite solution set or if there is no
 solution.

 */


public class LUFactorization {

    public static Vector LWY(double[][] lowermatrix, double[] rhsvector, int numberOfRows) {
        Vector W = new Vector(numberOfRows);
        double[] tempW = new double[numberOfRows];

        //Find resultant vector W given lower matrix and Right hand side vector
        for (int i = 0; i < numberOfRows; i++) {

            double subtracter = 0.0;
            for (int j = 0; j <= i; j++) {
                if (j < i) {
                    subtracter += lowermatrix[i][j] * tempW[j];
                }
                tempW[i] = (rhsvector[i] - subtracter) / lowermatrix[i][i];
            }
        }


        W.setVector(tempW);
        return W;
    }

    public static Vector UXW(double[][] uppermatrix, double[] Wvector, int numberOfRows) {

        Vector X = new Vector(numberOfRows);
        double[] tempX = new double[numberOfRows];

        //Find resultant vector X given upper matrix and W
        for (int i = numberOfRows - 1; i >= 0; i--) {

            double subtracter = 0.0;
            for (int j = numberOfRows - 1; j >= i; j--) {
                if (j > i) {
                    subtracter += uppermatrix[i][j] * tempX[j];
                }
                tempX[i] = (Wvector[i] - subtracter) / uppermatrix[i][i];
            }
        }

        X.setVector(tempX);
        return X;
    }

    public static Matrix LUFactorization(double[][] matrix, int numberOfRows) {

        Matrix tempMatrix = new Matrix(matrix);

        double[][] lowerMatrix = new double[numberOfRows][numberOfRows];


        //Set lower matrix
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = i; j < numberOfRows; j++) {
                if (i == j) {
                    lowerMatrix[i][j] = 1.0;
                } else {
                    lowerMatrix[i][j] = 0.0;
                }
            }
        }

        //Row-reduce upper matrix and build lower matrix
        for (int i = 1; i < numberOfRows; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[j][j] != 0) {

                    double multiplier = - matrix[i][j] / matrix[j][j];
                    lowerMatrix[i][j] = - multiplier;
                    for (int k = 0; k < numberOfRows; k++) {

                        matrix[i][k] = multiplier * matrix[j][k] + matrix[i][k];

                    }
                }
            }
        }
        tempMatrix.setUpperMatrix(matrix);
        tempMatrix.setLowerMatrix(lowerMatrix);
        return tempMatrix;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter number of rows/columns:");
        int numberOfRows = scanner.nextInt();

        //set BufferedReader objects to read files

        //SET FILES TO YOUR OWN DIRECTORY
        BufferedReader bfr = new BufferedReader(new FileReader(""));
        BufferedReader bfrRHS = new BufferedReader(new FileReader(""));


        //Set print stream to new file named "LUFactorization.txt"
        PrintStream o = new PrintStream(new File("LUFactorization.txt"));
        PrintStream console = System.out;
        System.setOut(o);



        System.out.println("");
        double[][] matrix = new double[numberOfRows][numberOfRows];
        double[] vector = new double[numberOfRows];

        int i = 0;
        String line;
        System.out.println("Original Matrix");

        //read input of matrix.txt
        while ((line = bfr.readLine()) != null) {

            line = ' ' + line;
            System.out.println(line);

            if (line.charAt(line.length() - 1) != ' ') {
                line += ' ';
            }

            String[] stringList = new String[numberOfRows];
            int index = 0;
            int indexOfLastSpace = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == ' ' && j != 0) {

                    stringList[index] = line.substring(indexOfLastSpace+1, j);
                    matrix[i][index] = Double.parseDouble(stringList[index]);
                    index++;
                    indexOfLastSpace = j;
                }
            }
            i++;
        }

        //read input from rhs.txt
        line = bfrRHS.readLine();

        line = ' ' + line;
        System.out.printf("\nRHS: " + line + "\n");
        int index = 0;
        int indexOfLastSpace = 0;

        String[] rhsList = new String[numberOfRows];
        if (line.charAt(line.length() - 1) != ' ') {
            line += ' ';
        }
        for (int k = 0; k < line.length(); k++) {
            if (line.charAt(k) == ' ' && k != 0) {
                rhsList[index] = line.substring(indexOfLastSpace+1, k);
                vector[index] = Double.parseDouble(rhsList[index]);
                index++;
                indexOfLastSpace = k;
            }
        }

        //set the matrix
        Matrix realMatrix = new Matrix(matrix);

        //call the LU Factorization method and get time
        Instant start = Instant.now();

        Matrix editedMatrix = LUFactorization(realMatrix.getMatrix(), numberOfRows);

        Vector W = LWY(editedMatrix.getLowerMatrix(), vector, numberOfRows);

        Vector X = UXW(editedMatrix.getUpperMatrix(), W.getVector(), numberOfRows);

        Instant end = Instant.now();

        Duration timeElapsed = Duration.between(start, end);

        //Print the outcomes
        System.out.printf("\nUpper Matrix\n");

        for (int k = 0; k < numberOfRows; k++) {
            System.out.println("");
            for (int j = 0; j < numberOfRows; j++) {
                if (Math.abs(editedMatrix.getUpperMatrix()[k][j]) < 0.01) {
                    System.out.printf("0.00 ");
                } else {
                    System.out.printf("%.2f ", editedMatrix.getUpperMatrix()[k][j]);
                }
            }
        }

        System.out.printf("\n\nLower Matrix\n");

        for (int k = 0; k < numberOfRows; k++) {
            System.out.println("");
            for (int j = 0; j < numberOfRows; j++) {
                if (Math.abs(editedMatrix.getLowerMatrix()[k][j]) < 0.01) {
                    System.out.printf("0.00 ");
                } else {
                    System.out.printf("%.2f ", editedMatrix.getLowerMatrix()[k][j]);
                }
            }
        }

        System.out.printf("\n\nW Vector\n");

        for (int k = 0; k < numberOfRows; k++) {
            System.out.println("");
            System.out.printf("%.2f ", W.getVector()[k]);
        }

        System.out.printf("\n\nX Vector, the Solution\n");

        //First, we are going to test if the solution has NaN or Infinity in the set

        boolean isUnique = true;
        for (int k = 0; k < numberOfRows; k++) {
            if ((Double.isNaN(X.getVector()[k]) || Double.isInfinite(X.getVector()[k])) && isUnique) {
                System.out.println("\nSystem either has an infinite solution set or no solution.");
                isUnique = false;
            }
        }

        if (isUnique) {
            for (int k = 0; k < numberOfRows; k++) {
                System.out.println("");
                System.out.printf("%.5f ", X.getVector()[k]);
            }
        }

        //Print out time taken to complete the LU Decomposition. This does not include file I/O time

        System.out.println("\n\nTime taken for algorithm: " + timeElapsed.toMillis() + " milliseconds");
        double add = 0.0;
        for (int k = 0; k < numberOfRows; k++) {
            add += X.getVector()[k] * realMatrix.getMatrix()[0][k];
        }
        if (Math.abs(add - vector[0]) < 0.01) {
            System.out.println("Verified system by checking first line.");
        }

    }

}
