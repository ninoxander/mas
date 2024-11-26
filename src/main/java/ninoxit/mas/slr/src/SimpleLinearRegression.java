package ninoxit.mas.slr.src;

import ninoxit.mas.discretemaths.DiscreteMaths;

public class SimpleLinearRegression {


    public static double[] linearRegression(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("Los arreglos x e y deben tener el mismo tamaño.");
        }

        int n = x.length;

        // Σx_i
        double sumX = DiscreteMaths.sum(x);
        // Σy_i
        double sumY = DiscreteMaths.sum(y);
        // Σx_i * y_i
        double sumXy = DiscreteMaths.weightedSum(x, y);
        // Σx_i^2
        double sumX2 = DiscreteMaths.sum(DiscreteMaths.power(x, 2));

        double m = (n * sumXy - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double b = (sumY - m * sumX) / n;

        return new double[] {m, b};
    }

    public static double[] predict(double[] x, double[] coefficients) {
        double m = coefficients[0];
        double b = coefficients[1];

        double[] predictions = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            predictions[i] = m * x[i] + b;
        }
        return predictions;
    }

    public static double meanSquaredError(double[] actual, double[] predicted) {
        if (actual.length != predicted.length) {
            throw new IllegalArgumentException("Los arreglos deben tener el mismo tamaño.");
        }

        double errorSum = 0.0;
        for (int i = 0; i < actual.length; i++) {
            errorSum += Math.pow(actual[i] - predicted[i], 2);
        }
        return errorSum / actual.length;
    }
}
