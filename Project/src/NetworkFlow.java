import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class NetworkFlow
{
    private int source;
    private int target;
    private int numberOfNodes;
    private int[] levelGraph;

    // This is essentially the Adjacency List as the Adjacent Edges are a property of the Vertex Object
    private Vertex[] vertices;

    // Constructor for Network Flow
    public NetworkFlow(int source, int target, int numberOfNodes)
    {
        this.source = source;
        this.target = target;
        this.numberOfNodes = numberOfNodes;
        this.vertices = new Vertex[numberOfNodes];
    }

    // Getters and Setters for Network Flow

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

    public int[] getLevelGraph()
    {
        return levelGraph;
    }

    public void setLevelGraph(int[] levelGraph)
    {
        this.levelGraph = levelGraph;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }

    /**
     * This Method is used to create an Edge and a Reverse Edge correspondent to this Edge, where both Edges are added
     * to the Adjacency List. The Reason why a Reverse Edge is made is because ...
     *
     * @param vertexOne The First Node or Vertex in an Edge
     * @param vertexTwo The Second Node or Vertex in an Edge
     * @param capacity The Capacity of an Edge meaning the maximum amount of Flow an Edge can handle
     */
    public void addEdgeToAdjacencyList(int vertexOne, int vertexTwo, int capacity)
    {
        Edge edge = new Edge(vertexOne, vertexTwo, capacity);
        Edge reversedEdge = new Edge(vertexTwo, vertexOne, 0);

        edge.setReversedEdge(reversedEdge);
        reversedEdge.setReversedEdge(edge);

        // Adding the Edge and the Reversed Edge to its Adjacent Vertices
        List<Edge> adjacentEdgesOne = vertices[vertexOne].getAdjacentEdges();
        adjacentEdgesOne.add(edge);

        List<Edge> adjacentEdgesTwo = vertices[vertexTwo].getAdjacentEdges();
        adjacentEdgesOne.add(reversedEdge);

        vertices[vertexOne].setAdjacentEdges(adjacentEdgesOne);
        vertices[vertexTwo].setAdjacentEdges(adjacentEdgesTwo);
    }

    /**
     * This Method will implement the Breadth First Search Algorithm to Traverse through the Network Flow to construct
     * a Level Graph, if more Flow can be made.
     *
     * @return A Boolean based on if a Path can be made from the Source to the Target
     */
    public boolean constructNetworkGraph()
    {
        levelGraph = new int[numberOfNodes];
        // -1 Indicates that the Node has not been visited in the Level Graph any number lower than the Source can be used to fill the Array
        Arrays.fill(levelGraph, -1);

        // Visiting the first Vertex in the Network Flow therefore setting the Level of the Source to 0 as it is the first Vertex in the Level Graph
        levelGraph[source]  = 0;

        // Upcasting a Queue Object to a LinkedList to have access to several functions to help the BFS, and adding Source first
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(source);

        while (! queue.isEmpty())
        {
            // The Poll Method removes the front-most Vertex in the Queue and returns it to our currentVertex Integer
            int currentVertex = queue.poll();

            // Looping through each Adjacent Edge in the AdjacencyList for the Current Vertex
            for ( Edge edge : vertices[currentVertex].getAdjacentEdges() )
            {
                /* If the Vertex in the Level Graph is not visited (Meaning -1 which is less than 0) and there is a
                 * Residual Capacity of more than 0 so that more Flow can be put in to that Edge, we increase the Level
                 * of the Vertex in edge to the Parent Vertex plus 1
                 */
                if ((levelGraph[edge.getVertexTwo()] < 0) && (edge.getFlow() < edge.getCapacity()))
                {
                    levelGraph[edge.getVertexTwo()] = levelGraph[edge.getVertexOne()] + 1;
                    queue.offer(edge.getVertexTwo());
                }
            }
        }

        return levelGraph[target] != -1;
    }


    /**
     * This Method implements the Depth First Search Algorithm to attempt to Augment Paths for a Vertex.
     *
     * @param vertex This is the Vertex of which we are trying to Augment Paths to
     * @param flow This is the Flow sent to the Edge
     *
     * @return The Bottle Neck Value meaning the Edge with the Lowest Capacity in an Augmented Path Flow
     */
    public int augmentPath(int vertex, int flow)
    {
        // If the Target is reached we must return the flow
        if (vertex == target)

            return flow;

        int edgeCount = 0;

        // Going through all visited Edges and selected unvisited Edges

        for ( boolean edgeVisited : vertices[vertex].getVisitedEdges() )
        {
            if (! edgeVisited)
            {
                Edge edge = vertices[vertex].getAdjacentEdges().get(edgeCount);

                /* In order to include an Edge in a Path it must have space to carry more Flow (Residual Capacity
                 * is the Capacity minus the Flow so the remaining Flow) and the Level of second Vertex has to be
                 * greater than the Level of the Parent Vertex according to the Dinic's Algorithm a Path cannot include
                 * a Flow from an Edge on the Same Level or Lower */
                if ( (edge.getResidualCapacity() > 0 ) && ( levelGraph[edge.getVertexTwo()] > levelGraph[vertex] ) )
                {
                    /* Here we Recursively call DFS method and augment a Path till Target is reached thereby each time
                     * we get the returned Bottle Neck Factor */

                    int bottleneckFlow = augmentPath(edge.getVertexTwo(), Math.min(flow, edge.getResidualCapacity()));

                    if (bottleneckFlow > 0)
                    {
                        edge.setFlow(edge.getFlow() + bottleneckFlow);
                        Edge reversedEdge = edge.getReversedEdge();
                        reversedEdge.setFlow(reversedEdge.getFlow() - bottleneckFlow);

                        return bottleneckFlow;
                    }
                }

                // We now make the Edge Visited
                vertices[vertex].getVisitedEdges()[edgeCount] = true;

            }

            edgeCount++;
        }

        return 0;
    }

    /**
     * Level Graph -
     *
     *
     * This Method Implements the Dinic's Algorithm to find the Maximum Flow of the Network Flow. Here is a brief
     * explanation on how it works, first a Level Graph is constructed using BFS (Breadth First Search)
     *
     * @return The Max Flow
     */
    public int getMaxFlow()
    {
        int maxFlow = 0;

        while (constructNetworkGraph())
        {
            /* Trying to Augment as many Paths as possible till the flow is not 0 meaning for the specific
             * Construct of the Level Graph all Edges have been visited. This is done using DFS the parameters are
             * self explanatory but the reason why we first Augment a Path with a Flow equals to Infinity is because
             *
             */
            for(long flow = augmentPath(source, (int) Double.POSITIVE_INFINITY); flow != 0;
                     flow = augmentPath(source, (int) Double.POSITIVE_INFINITY))

                //Maximum flow will get increased by adding up every 'bottleNeck' value(f) [when path from s to t is found.]
                maxFlow += flow;
        }

        return maxFlow;
    }


    /**
     * This Overridden Method is called when the NetworkFlow Object is printed, but usually the Object's reference is printed.
     * Thereby we override this Method and return the NetworkFlow Object's Attributes in a nicely presented way.
     *
     * @return The Description of the NetworkFlow Object that we are returning
     */
    @Override
    public String toString() {
        return "\nSource: " + source +
                " | Target: " + target +
                " | Number Of Nodes: " + numberOfNodes;
    }
}