import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex
{
    private int vertexName;
    private boolean[] visitedEdges;
    private List<Edge> adjacentEdges;

    public Vertex(int vertexName, int numberOfAdjacentEdges)
    {
        this.vertexName = vertexName;
        visitedEdges = new boolean[numberOfAdjacentEdges];
        Arrays.fill(visitedEdges, false);
        this.adjacentEdges = new ArrayList<>();
    }

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
}