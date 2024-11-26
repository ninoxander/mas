package ninoxit.mas.slr.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ninoxit.mas.dataset.SLRDataset;
import ninoxit.mas.slr.src.SimpleLinearRegression;

public class SLRBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        int limit = 0;
        ACLMessage msg = myAgent.receive();

        if(msg != null && limit == 0) {
            System.out.println("Message received: " + msg);

            double[] x = SLRDataset.getX();
            double[] y = SLRDataset.getY();

            double[] coefficients = SimpleLinearRegression.linearRegression(x, y);
            double slope = coefficients[0];
            double intercept = coefficients[1];

            System.out.println("Slope (m): " + slope);
            System.out.println("Intercept (b): " + intercept);

            double[] predictions = SimpleLinearRegression.predict(x, coefficients);

            System.out.println("Predictions:");
            for (double prediction : predictions) {
                System.out.println(prediction);
            }

            double mse = SimpleLinearRegression.meanSquaredError(y, predictions);
            System.out.println("MSE: " + mse);

            limit = 1;
        }else {
            block();
        }



    }
}
