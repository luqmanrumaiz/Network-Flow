/**
 * Name - Luqman Rumaiz
 * IIT ID - 2018130
 * UOW ID - W1761767
 */

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class GraphGUI
{
    /*
     * References
     * ---------
     * https://graphstream-project.org/doc/Tutorials/Getting-Started/
     * https://graphstream-project.org/doc/Tutorials/Graph-Visualisation/1.0/
     * https://stackoverflow.com/questions/37530756/dont-close-swing-main-app-when-closing-graphstream
     */

    Graph graph;
    ArrayList<Edge> capacityAndFlow;

    // Constructor for Graph GUI
    public GraphGUI()
    {
        graph = new SingleGraph("Max Flow Graph");
        graph.setAttribute("ui.stylesheet", styleSheet);
        graph.setAutoCreate(true);
        graph.setStrict(false);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        capacityAndFlow = new ArrayList<>();
    }

    // Getters and Setters for Graph GUI

    public Graph getGraph()
    {
        return graph;
    }

    public void setGraph(Graph graph)
    {
        this.graph = graph;
    }


    /**
     * This Method adds a Vertex to the Graph
     *
     * @param vertex The Vertex to add to the Graph
     */
    public void addVertex(String vertex)
    {
        graph.addNode(vertex);
    }

    /**
     * This Method adds an Edge to the Graph
     *
     * @param vertexOne Vertex One of the Edge
     * @param vertexTwo Vertex Two of the Edge
     * @param capacity Capacity of the Edge
     * @param flow Flow of the Edge
     */
    public void addEdge(String vertexOne, String vertexTwo, int capacity, int flow)
    {
        graph.addEdge(vertexOne + vertexTwo, vertexOne, vertexTwo, true);
        graph.addAttribute("weight", capacity);
        // Adding the Capacity in a ArrayList to later set the Label for each Edge in order
        capacityAndFlow.add(new Edge(Integer.parseInt(vertexOne), Integer.parseInt(vertexTwo), capacity, flow));
    }

    public void display()
    {
        // If the Graph is Large (> 200) a custom StyleSheet with smaller looking Vertices is shown

        if (capacityAndFlow.size() > 200)

            graph.setAttribute("ui.stylesheet", styleSheetBigDataset);


        // This is to ensure that the whole application does not close when the Swing Window is closed
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);


        // Setting the Label for each Node and Edge
        for (Node node : graph)
        {
            // Using AtomicInteger as Edge is inside a Lambda
            AtomicInteger edgeCount = new AtomicInteger(0);

            node.setAttribute("ui.label", node.getId());
            node.getEachEdge().forEach(edge ->
            {
                if (edge.getNode0() == node)
                {
                    edge.setAttribute("ui.label", capacityAndFlow.get(edgeCount.intValue()).getFlow() + " / " +
                            capacityAndFlow.get(edgeCount.intValue()).getCapacity());
                }
                edgeCount.getAndIncrement();
            });

        }
    }

    // This is the CSS StyleSheet for a Regular Graph
    protected String styleSheet =
            "node {" +
                    "	fill-color: black;" +
                    "   text-size: 16px;" +
                    "   text-style: bold;" +
                    "   padding: 100px;" +
                    "   size: 10px, 15px;" +
                    "   shape: box;" +
                    "   fill-color: green;" +
                    "   stroke-mode: plain;" +
                    "   stroke-color: yellow;"+
                    "   text-background-color: white;" +
                    "}" +
            "edge {" +
                    "	fill-color: black;" +
                    "   text-size: 16px;" +
                    "   text-style: bold;" +
                    "   padding: 200px;" +
                    "   text-background-color: white;" +
                    "}";

    // This is the CSS StyleSheet for a Fairly Large Graph
    protected String styleSheetBigDataset =
            "node {" +
                    "	size: 3px;" +
                    "   fill-color: #777;" +
                    "   text-mode: hidden;" +
                    "   z-index: 0;" +
                    "   size: 10px, 15px;" +
                    "   shape: box;" +
                    "   fill-color: green;" +
                    "   stroke-mode: plain;" +
                    "   stroke-color: yellow;"+
                    "}" +
            "edge {" +
                    "	shape: line;" +
                    "   fill-color: #222;" +
                    "   arrow-size: 3px, 2px;" +
                    "}";
}