package trickytasks.task1;

public interface IMatrixWorker {

    /**Outputs a matrix
     *
     * @param m
     */
    public void print(double[][] m);

    /** looks to see if matrices are of the same dimension. That is, the same number of rows and columns. As well as rectangularity.
     *
     * @param m1
     * @param m2
     * @return true, if matrices m1 and m2 have the same number of rows and columns
     */
    public boolean haveSameDimension(double[][] m1, double[][] m2);

    /** adds matrix m1 and matrix m2 and returns the result of their addition
     *
     * @param m1
     * @param m2
     * @return matrix of addition of two matrices m1 and m2
     */
    public double[][] add(double[][] m1, double[][] m2);

    /** subtracts the matrix m2 from the matrix m1 and returns the result of their subtraction
     *
     * @param m1
     * @param m2
     * @return the difference matrix of two matrices m1 and m2
     */
    public double[][] subtract(double[][] m1, double[][] m2);


    /** Checks if the number of rows of the matrix m1 is equal to the number of columns of the matrix m2.
     * If so, it multiplies the m1 matrix by the m2 matrix. Returns the matrix of their multiplication.
     *
     * @param m1
     * @param m2
     * @return the matrix of their multiplication of two matrices m1 and m2
     */
    public double[][] multiply(double[][] m1, double[][] m2);
}

