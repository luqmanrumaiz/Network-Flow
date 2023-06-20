public class Edge
{
    private int vertexOne;
    private int vertexTwo;
    private int capacity;
    private int flow;
    private Edge reversedEdge;

    // Constructor for the Edge Class
    public Edge(int vertexOne, int vertexTwo, int capacity)
    {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.capacity = capacity;
        flow = 0;
    }

    public Edge(int vertexOne, int vertexTwo, int capacity, int flow)
    {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.capacity = capacity;
        this.flow = flow;
    }

    // Getters and Setters for the Edge Class

    public int getVertexOne()
    {
        return vertexOne;
    }

    public void setVertexOne(int vertexOne)
    {
        this.vertexOne = vertexOne;
    }

    public int getVertexTwo()
    {
        return vertexTwo;
    }

    public void setVertexTwo(int vertexTwo)
    {
        this.vertexTwo = vertexTwo;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public int getFlow()
    {
        return flow;
    }

    public void setFlow(int flow)
    {
        this.flow = flow;
    }

    public Edge getReversedEdge()
    {
        return reversedEdge;
    }

    public void setReversedEdge(Edge reversedEdge)
    {
        this.reversedEdge = reversedEdge;
    }

    /**
     * This Method returns the Residual Capacity of an Edge, in other words it is the Capacity of Flow that an Edge can receive.
     *
     * @return The Residual Capacity
     */
    public int getResidualCapacity()
    {
        return capacity - flow;
    }

    @Override
    public String toString()
    {
        return "{ " + vertexOne + " ~> " + vertexTwo + " | Capacity=" + capacity + " | Flow=" + flow + " }";
    }
}
