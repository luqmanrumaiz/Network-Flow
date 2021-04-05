import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex
{
    private int vertexName;
    private boolean[] visitedEdges;
    private List<Edge> adjacentEdges;

    // Constructor for the Vertex Class
    public Vertex(int vertexName)
    {
        this.vertexName = vertexName;
        this.adjacentEdges = new ArrayList<>();
    }

    // Getters and Setters for the Vertex Class

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

    /**
     * This Method is used to remove an Edge from the Adjacent Edges in this Vertex
     *
     * @param edgeToRemove The Edge that has to be removed
     */
    public void removeEdge(Edge edgeToRemove)
    {
        adjacentEdges.remove(edgeToRemove.getReversedEdge());
        adjacentEdges.remove(edgeToRemove);

        // Increasing the size of the Visited Edges Array as there is a change in the size of the Adjacent Edges
        visitedEdges = new boolean[adjacentEdges.size()];
        Arrays.fill(visitedEdges, false);
    }

    /**
     * This Method is used to change the Capacity of the Edges of the Adjacent Edges in this Vertex
     *
     * @param edgeToModify The Edge to be Edited
     * @param newCapacity The Updated Capacity
     */
    public void editEdge(Edge edgeToModify, int newCapacity)
    {
        int index = adjacentEdges.indexOf(edgeToModify);

        edgeToModify.setCapacity(newCapacity);
        adjacentEdges.set(index, edgeToModify);
    }

    /**
     * This Method is used to add a new Adjacent Edge
     *
     * @param edgeToInsert The new Adjacent Edge
     */
    public void insertEdge(Edge edgeToInsert)
    {
        adjacentEdges.add(edgeToInsert);

        // Increasing the size of the Visited Edges Array as there is a change in the size of the Adjacent Edges
        visitedEdges = new boolean[adjacentEdges.size()];
        Arrays.fill(visitedEdges, false);
    }

    @Override
    public String toString()
    {
        return "\nVertex: " + vertexName + " | Adjacent Edges: " + adjacentEdges;
    }
}