import java.util.Set;

public class NetworkFlow
{
    int source;
    int target;
    int numberOfNodes;
    int maximumFlow;
    Set<Flow> flows;

    public NetworkFlow(int source, int target, int numberOfNodes, int maximumFlow, Set<Flow> flows)
    {
        this.source = source;
        this.target = target;
        this.numberOfNodes = numberOfNodes;
        this.maximumFlow = maximumFlow;
        this.flows = flows;
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

    public int getMaximumFlow()
    {
        return maximumFlow;
    }

    public void setMaximumFlow(int maximumFlow)
    {
        this.maximumFlow = maximumFlow;
    }

    public Set<Flow> getFlows()
    {
        return flows;
    }

    public void setFlows(Set<Flow> flows)
    {
        this.flows = flows;
    }
}