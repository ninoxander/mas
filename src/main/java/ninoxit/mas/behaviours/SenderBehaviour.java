package ninoxit.mas.behaviours;

import jade.core.AID;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import ninoxit.mas.dataset.*;
import ninoxit.mas.utils.JSONParser;

public class SenderBehaviour extends WakerBehaviour {
    public SenderBehaviour(jade.core.Agent agent, long timeout) {
        super(agent, timeout);
    }

    @Override
    public void onWake() {
        double[] x = MLRDataset.getX();
        double[] y = MLRDataset.getY();

        String json = JSONParser.toJson(x, y);

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setContent(json);
        msg.addReceiver(new AID("RouterAgent", AID.ISLOCALNAME));

        myAgent.send(msg);
    }
}
