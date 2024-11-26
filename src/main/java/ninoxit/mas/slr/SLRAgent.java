package ninoxit.mas.slr;

import jade.core.Agent;
import ninoxit.mas.slr.behaviours.SLRBehaviour;

public class SLRAgent extends Agent{
    @Override
    protected void setup() {
        addBehaviour(new SLRBehaviour());
    }
}