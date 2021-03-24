public class Flow
{
    private int vertex;
    private int edge;
    private int capacity;

    public Flow(int vertices, int edge, int capacity)
    {
        this.vertex = vertices;
        this.edge = edge;
        this.capacity = capacity;
    }

    public int getVertex()
    {
        return vertex;
    }

    public void setVertex(int vertex)
    {
        this.vertex = vertex;
    }

    public int getEdge()
    {
        return edge;
    }

    public void setEdge(int edge)
    {
        this.edge = edge;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "\nFlow{" +
                "Vertices: " + vertex +
                ", Edges: " + edge +
                ", Capacity: " + capacity +
                '}';
    }

}