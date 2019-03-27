# LUDecomposition
This is a program designed to read matrix data from a text file and solve the system using LU Decomposition.

The program takes in input in this format:

1 5 9
-4 3 6
2 3 6

This should be the complete file, from lines 6-8 as the example of the matrix. The program automatically parses the 
numbers based on the spaces inbetween them. The right hand side column vector will take the form of:

5 -3 7

The output will be, from lines 17-49:

Original Matrix
 1 5 9
 -4 3 6
 2 3 6

RHS:  5 -3 7

Upper Matrix

1.00 5.00 9.00 
0.00 23.00 42.00 
0.00 0.00 0.78 

Lower Matrix

1.00 0.00 0.00 
-4.00 1.00 0.00 
2.00 -0.30 1.00 

W Vector

5.00 
17.00 
2.17 

X Vector, the Solution

1.66667 
-4.33333 
2.77778 

Time taken for algorithm: 0 milliseconds
Verified system by checking first line.

//End of program output
Also included in the repository is a program designed to create a random matrix and right hand side column vector
to test the program with. This also prints the matrix and column vector in the correct format, if you need
formatting help.

For further questions, email me at lukeadow8@gmail.com
