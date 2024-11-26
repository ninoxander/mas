package ninoxit.mas.dataset;

public class MLRDataset {
    private static double[] X = {
            108, 115, 106, 97, 95, 91, 97, 83, 78, 54, 67, 56, 53, 61, 115, 81,
            78, 200, 45, 99, 32, 25, 28, 90, 89
    };

    private static double[] Y = {
            95, 96, 95, 97, 97, 94, 95, 92, 86, 73, 80, 65, 69, 77, 96, 87,
            89, 90, 63, 95, 61, 55, 56, 94, 93
    };


    public static double[] getX() {
        return X;
    }

    public static double[] getY() {
        return Y;
    }
}
