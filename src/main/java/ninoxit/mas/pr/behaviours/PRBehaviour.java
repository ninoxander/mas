package ninoxit.mas.pr.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import ninoxit.mas.dataset.SLRDataset;
import ninoxit.mas.pr.src.PolynomialRegression;

public class PRBehaviour extends CyclicBehaviour {
    private int degree = 0;

    public PRBehaviour(int degree) {
        this.degree = degree;
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        System.out.println("Received message: " + msg);

        if (msg != null) {
            System.out.println("Processing message content: " + msg.getContent());

            double[] x = SLRDataset.getX();
            double[] y = SLRDataset.getY();

            System.out.println("Running Polynomial Regression with data points (x, y):");
            for (int i = 0; i < x.length; i++) {
                System.out.println("(" + x[i] + ", " + y[i] + ")");
            }

            double[] coefficients = PolynomialRegression.polynomialRegression(x, y, degree);

            System.out.println("Polynomial Regression coefficients (degree " + degree + "):");
            for (int i = 0; i < coefficients.length; i++) {
                System.out.println("Beta " + i + ": " + coefficients[i]);
            }

            double[] y_pred = PolynomialRegression.predict(x, coefficients);

            double rSquared = PolynomialRegression.calculateRSquared(y, y_pred);
            System.out.println("R^2: " + rSquared);

            double adjustedRSquared = PolynomialRegression.calculateAdjustedRSquared(rSquared, x.length, degree);
            System.out.println("Adjusted R^2: " + adjustedRSquared);

            double[] standardErrors = PolynomialRegression.calculateStandardError(x, y, coefficients, degree);
            System.out.println("Standard Errors of Beta coefficients:");
            for (int i = 0; i < standardErrors.length; i++) {
                System.out.println("SE(Beta " + i + "): " + standardErrors[i]);
            }
        } else {
            block();
        }
    }
}
