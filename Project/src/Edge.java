import java.util.Objects;

public class Edge
{

    private Vertex vertexOne;
    private Vertex vertexTwo;
    private int capacity;
    private int flow;

    public Edge(Vertex vertexOne, Vertex vertexTwo, int capacity)
    {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.capacity = capacity;
        flow = 0;
    }

    public Edge(Vertex vertexOne, Vertex vertexTwo, int capacity, int flow)
    {
        this.vertexOne = vertexOne;
        this.vertexTwo = vertexTwo;
        this.capacity = capacity;
        this.flow = flow;
    }

    public Vertex getVertexOne()
    {
        return vertexOne;
    }

    public void setVertexOne(Vertex vertexOne)
    {
        this.vertexOne = vertexOne;
    }

    public Vertex getVertexTwo()
    {
        return vertexTwo;
    }

    public void setVertexTwo(Vertex vertexTwo)
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

    public boolean isEmpty()
    {
        return flow < capacity;
    }

    /* If the equals method returns true when comparing two Objects that are equal, then the hashcode() method is called to use a Hash Algorithm on the Current Object
     * and then the other Object, an Integer is returned and if both Objects are equal they will have the same Integer value that is returned*/
    @Override
    public int hashCode()
    {
        return Objects.hash(getVertexOne(), getVertexTwo(), getFlow(), getCapacity());
    }

    @Override
    public boolean equals(Object object)
    {

        // checking if both the object references are
        // referring to the same object.
        if(this == object)
            return true;

        if(object == null || object.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        Edge edge = (Edge) object;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (edge.vertexOne == this.vertexOne && edge.vertexTwo == this.vertexTwo && edge.flow == this.flow
                && edge.capacity == this.capacity);
    }

    @Override
    public String toString() {
        return "\n" + capacity+ " (" + vertexOne.getVertexName() + ", " + vertexTwo.getVertexName() + ")";
    }

}