public class Matrix {
    private double[][] matrix;
    private double[][] lowerMatrix;
    private double[][] upperMatrix;

    public Matrix (double[][] matrix) {

        this.matrix = new double[matrix.length][matrix.length];
        this.lowerMatrix = new double[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == j) {
                    this.lowerMatrix[i][j] = 1.0;
                } else {
                    this.lowerMatrix[i][j] = 0.0;
                }
            }
        }
    }

    public void setLowerMatrix(double[][] lowerMatrix) {
        this.lowerMatrix = lowerMatrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setUpperMatrix(double[][] upperMatrix) {
        this.upperMatrix = upperMatrix;
    }

    public double[][] getLowerMatrix() {
        return this.lowerMatrix;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public double[][] getUpperMatrix() {
        return this.upperMatrix;
    }

}
