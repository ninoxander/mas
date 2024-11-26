package ninoxit.mas.utils;

import java.util.Arrays;

public class JSONParser {
    public static String toJson(Object X, double[] Y) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        jsonBuilder.append("\"X\": ");
        if (X instanceof double[][]) {
            jsonBuilder.append("[");
            double[][] X2D = (double[][]) X;
            for (int i = 0; i < X2D.length; i++) {
                jsonBuilder.append("[");
                for (int j = 0; j < X2D[i].length; j++) {
                    jsonBuilder.append(X2D[i][j]);
                    if (j < X2D[i].length - 1) {
                        jsonBuilder.append(", ");
                    }
                }
                jsonBuilder.append("]");
                if (i < X2D.length - 1) {
                    jsonBuilder.append(", ");
                }
            }
            jsonBuilder.append("]");
        } else if (X instanceof double[]) {
            jsonBuilder.append(Arrays.toString((double[]) X));
        } else {
            throw new IllegalArgumentException("El formato de X no es válido. Debe ser 1D o 2D.");
        }

        jsonBuilder.append(", \"Y\": ");
        jsonBuilder.append(Arrays.toString(Y));

        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }

    public static Object[] fromJson(String jsonString) {
        jsonString = jsonString.trim().replaceAll("\\s+", "");

        if (jsonString.contains("[[")) {
            String xPart = jsonString.substring(jsonString.indexOf("[["), jsonString.indexOf("]]") + 2);
            String[] rows = xPart.replace("[[", "").replace("]]", "").split("],\\[");
            double[][] X = new double[rows.length][];
            for (int i = 0; i < rows.length; i++) {
                X[i] = Arrays.stream(rows[i].split(",")).mapToDouble(Double::parseDouble).toArray();
            }

            String yPart = jsonString.substring(jsonString.lastIndexOf("[") + 1, jsonString.lastIndexOf("]"));
            double[] Y = Arrays.stream(yPart.split(",")).mapToDouble(Double::parseDouble).toArray();

            return new Object[]{X, Y};
        } else {
            String xPart = jsonString.substring(jsonString.indexOf("[") + 1, jsonString.indexOf("]"));
            double[] X = Arrays.stream(xPart.split(",")).mapToDouble(Double::parseDouble).toArray();

            String yPart = jsonString.substring(jsonString.lastIndexOf("[") + 1, jsonString.lastIndexOf("]"));
            double[] Y = Arrays.stream(yPart.split(",")).mapToDouble(Double::parseDouble).toArray();

            return new Object[]{X, Y};
        }
    }
}
