package trickytasks.task1;

public class MatrixW implements IMatrixWorker{

    @Override
    public void print(double[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public boolean haveSameDimension(double[][] m1, double[][] m2) {
        //First check: are matrices rectangular
        if (!(isRectangular(m1) && isRectangular(m2))) {
            return false;
        }
        //Second check: equality of the number of rows in arrays
        if (m1.length != m2.length) {
            return false;
        }
        //Check the third: the equality of the number of columns in the arrays
        // (Since the matrices are rectangular, it is enough to check only the length of their first rows)
        if (m1[0].length != m2[0].length) {
            return false;
        }
        return true;
    }

    //check for rectangularity
    private boolean isRectangular (double[][] m1){
        for (int i = 1; i < m1.length; i++) {
            if (m1[0].length != m1[i].length) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double[][] add(double[][] m1, double[][] m2) {
        //We create an array in which the results of addition m1 and m2 will be written
        double[][] matrixplus = new double[m1.length][m1[0].length];
        if (!haveSameDimension(m1, m2)) {
            throw new NullPointerException("The matrices m1 and m2 cannot be added, since they are of different dimensions");
        }
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                matrixplus[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return matrixplus;
    }

    @Override
    public double[][] subtract(double[][] m1, double[][] m2) {
        //We create an array in which the results of subtraction m1 and m2 will be written
        double[][] matrixminus = new double[m1.length][m1[0].length];
        if (!haveSameDimension(m1, m2)) {
            throw new NullPointerException("The matrix m2 cannot be subtracted from m1, since they are of different dimensions");
        }
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                matrixminus[i][j] = m1[i][j] - m2[i][j];
            }
        }

        return matrixminus;
    }

    @Override
    public double[][] multiply(double[][] m1, double[][] m2) {
        //First check: are matrices rectangular
        if (!(isRectangular(m1) && isRectangular(m2))) {
            throw new NullPointerException("Matrices m1 and m2 cannot be multiplied, since one of them (or both) is not rectangular");
        }
        //Second check: the number of columns of the matrix m1 must be equal to the number of rows of the matrix m2
        if (m1[0].length != m2.length) {
            throw new NullPointerException("The matrices m1 and m2 cannot be multiplied, since the number of columns of the matrix m1 is not equal to the number of rows of the matrix m2");
        }
        //We create an array in which the results of multiply m1 and m2 will be written
        double[][] vectormultiply = new double[m2.length][m2[0].length];
        for (int i = 0; i < m1.length; ++i) {
            for (int j = 0; j < m2[0].length; ++j) {
                for (int k = 0; k < m2.length; ++k) {
                    vectormultiply[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return vectormultiply;
    }
}
