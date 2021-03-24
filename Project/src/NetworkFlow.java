import java.util.Arrays;

public class NetworkFlow
{
    private int source;
    private int target;
    private int numberOfNodes;
    private Flow[] flows;

    public NetworkFlow(int source, int target, int numberOfNodes, Flow[] flows)
    {
        this.source = source;
        this.target = target;
        this.numberOfNodes = numberOfNodes;
        this.flows = flows;
    }

    public NetworkFlow()
    {
        flows = new Flow[numberOfNodes];
    }

    public int getSource()
    {
        return source;
    }

    public void setSource(int source)
    {
        this.source = source;
    }

    public int getTarget()
    {
        return target;
    }

    public void setTarget(int target)
    {
        this.target = target;
    }

    public int getNumberOfNodes()
    {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
    }

    public Flow[] getFlows()
    {
        return flows;
    }

    public void setFlows(Flow[] flows)
    {
        this.flows = flows.clone();
    }

    @Override
    public String toString() {
        return "Source: " + source +
                "\nTarget:" + target +
                "\nNumber Of Nodes: " + numberOfNodes +
                "\nFlows: " + Arrays.toString(flows);
    }
}