import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex
{
    private int vertexName;
    private boolean[] visitedEdges;
    private List<Edge> adjacentEdges;

    public Vertex(int vertexName)
    {
        this.vertexName = vertexName;
        this.adjacentEdges = new ArrayList<>();
    }

    public boolean[] getVisitedEdges() {
        return visitedEdges;
    }

    public void setVisitedEdges(boolean[] visitedEdges) {
        this.visitedEdges = visitedEdges;
    }

    public int getVertexName() {
        return vertexName;
    }

    public void setVertexName(int vertexName) {
        this.vertexName = vertexName;
    }

    public List<Edge> getAdjacentEdges() {
        return adjacentEdges;
    }

    public void setAdjacentEdges(List<Edge> adjacentEdges) {
        this.adjacentEdges = adjacentEdges;
    }

    public boolean removeEdge(Edge edgeToRemove)
    {
        if ( adjacentEdges.contains(edgeToRemove) )
        {
            adjacentEdges.remove(edgeToRemove);
            visitedEdges = new boolean[adjacentEdges.size()];
            Arrays.fill(visitedEdges, false);
            return true;
        }

        return false;
    }


    @Override
    public String toString() {
        return "\nVertex: " + vertexName + " | Adjacent Edges: " + adjacentEdges;
    }
}