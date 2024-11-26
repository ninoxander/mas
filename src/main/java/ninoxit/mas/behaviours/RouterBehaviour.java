package ninoxit.mas.behaviours;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;

import ninoxit.mas.utils.Analyzer;
import ninoxit.mas.utils.JSONParser;

public class RouterBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        ACLMessage out = new ACLMessage(ACLMessage.REQUEST);
        System.out.println(msg);

        if (msg != null) {
            String message = msg.getContent();
            String type = Analyzer.analyzeDataset(message);

            Object[] data = JSONParser.fromJson(message);
            Object X = data[0];
            double[] Y = (double[]) data[1];

            System.out.println("Dataset type: " + type);
            switch (type) {
                case "MLR":
                    System.out.println("Running MLR");
                    out.setContent("Running MLR with dataset");
                    out.addReceiver(new AID("SLRAgent", AID.ISLOCALNAME));
                    System.out.println("Sending a message to MLR Agent: " + out.getContent());
                    myAgent.send(out);
                    break;
                case "SLR":
                    System.out.println("Running SLR");
                    /*
                    System.out.println("Running SLR");
                    out.setContent("Running SLR with dataset");
                    out.addReceiver(new AID("SLRAgent", AID.ISLOCALNAME));
                    System.out.println("Sending a message to SLR Agent: " + out.getContent());
                    myAgent.send(out);
                    */
                    break;
                case "PR":
                    System.out.println("Running PR");
                    out.setContent("Running PR with dataset");
                    out.addReceiver(new AID("PRAgent", AID.ISLOCALNAME));
                    System.out.println("Sending a message to PR Agent: " + out.getContent());
                    myAgent.send(out);
                    break;

                default:
                    System.out.println("Unknown dataset type: " + type);
                    break;
            }
        } else {
            block();
        }
    }
}
