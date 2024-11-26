package ninoxit.mas.utils;

public class Analyzer {
    public static String analyzeDataset(String jsonString) {
        Object[] data = JSONParser.fromJson(jsonString);

        Object X = data[0];
        double[] Y = (double[]) data[1];

        if (X instanceof double[][]) {
            return "MLR";
        } else if (X instanceof double[]) {
            return "SLR";
        } else {
            return "PR";
        }
    }
}