import java.util.Arrays;

public class NetworkFlow
{
    private String source;
    private String target;
    private int numberOfNodes;
    private Edge[] edges;

    public NetworkFlow(String source, String target, int numberOfNodes, Edge[] edges)
    {
        this.source = source;
        this.target = target;
        this.numberOfNodes = numberOfNodes;
        this.edges = edges;
    }

    public NetworkFlow()
    {
        edges = new Edge[numberOfNodes];
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
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

    public Edge[] getEdges()
    {
        return edges;
    }

    public void setEdges(Edge[] edges)
    {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "\nSource: " + source +
                "\nTarget: " + target +
                "\nNumber Of Nodes: " + numberOfNodes +
                "\nEdges (Capacity (Vertex One, Vertex Two) ) : " + Arrays.toString(edges);
    }
}