package ninoxit.mas;

import jade.core.Agent;
import ninoxit.mas.behaviours.RouterBehaviour;

public class RouterAgent extends Agent {
    @Override
    public void setup() {
        addBehaviour(new RouterBehaviour());
    }
}
