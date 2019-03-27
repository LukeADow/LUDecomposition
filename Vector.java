public class Vector {
    private double[] vector;

    public Vector() {
        this.vector = null;
    }

    public Vector(int size) {
        this.vector = new double[size];
    }
    public Vector(double[] vector) {
        this.vector = vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public double[] getVector() {
        return vector;
    }
}
