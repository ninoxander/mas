package ninoxit.mas;

import jade.core.Agent;
import ninoxit.mas.behaviours.SenderBehaviour;

public class MainAgent extends Agent{
    @Override
    public void setup() {
        addBehaviour(new SenderBehaviour(this, 2000));
    }
}