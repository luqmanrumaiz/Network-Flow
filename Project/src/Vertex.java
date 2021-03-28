import java.util.Objects;

public class Vertex
{
    private String vertexName;
    private boolean visited;

    public Vertex(String vertexName)
    {
        this.vertexName = vertexName;
        visited = false;
    }

    public Vertex(String vertexName, boolean visited)
    {
        this.vertexName = vertexName;
        this.visited = visited;
    }

    public String getVertexName()
    {
        return vertexName;
    }

    public void setVertexName(String vertexName)
    {
        this.vertexName = vertexName;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    /* If the equals method returns true when comparing two Objects that are equal, then the hashcode() method is called to use a Hash Algorithm on the Current Object
     * and then the other Object, an Integer is returned and if both Objects are equal they will have the same Integer value that is returned*/
    @Override
    public int hashCode() {

        return Objects.hash(getVertexName(), isVisited());
    }

    @Override
    public boolean equals(Object object)
    {

        // checking if both the object references are
        // referring to the same object.
        if(this == object)
            return true;

        // it checks if the argument is of the
        // type Geek by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof Geek)) return false; ---> avoid.
        if(object == null || object.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        Vertex vertex = (Vertex) object;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (vertex.vertexName == this.vertexName && vertex.visited == this.visited);
    }
    @Override
    public String toString() {
        return "\nEdge{" +
                ", Edge: " + vertexName +
                ", Visited: " + visited +
                '}';
    }

}