public class Flow
{
    int vertices;
    int edges;
    int capacity;

    public Flow(int vertices, int edges, int capacity)
    {
        this.vertices = vertices;
        this.edges = edges;
        this.capacity = capacity;
    }

    public int getVertices()
    {
        return vertices;
    }

    public void setVertices(int vertices)
    {
        this.vertices = vertices;
    }

    public int getEdges()
    {
        return edges;
    }

    public void setEdges(int edges)
    {
        this.edges = edges;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
}