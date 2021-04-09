import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleApplication
{
    private static NetworkFlow networkFlow;
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("0oo - Welcome to the Network Flow Application - ooO");

        // When Option to Quit Program is selected run will be set to false thus ending the Loop
        boolean run = true;
        while (run)
        {
            System.out.print(
                    "\nHere below are the Options you can Execute,\n" +
                            "01: Initialize the Network Flow\n" +
                            "02: Find the Maximum Flow\n" +
                            "03: Insert an Edge\n" +
                            "04: Delete an Edge\n" +
                            "05: Edit the Capacity of an Edge\n" +
                            "06: View the Network Flow (CLI)\n" +
                            "07: View the Network Flow (GUI)\n" +
                            "08: Quit Program\n\n" +
                            "Desired Option: ");

            String selectedOption = INPUT.next();
            int option = 0;

            // Try Catch Block inorder to Validate Input that is not an Integer
            try
            {
                option = Integer.parseInt(selectedOption);

                // Printing Error Message if Option is not in range of the available options
                if (option < 1 || option > 8)
                {
                    System.out.println("\nThe Option you have entered is Invalid !!!");
                    continue;
                }
            }
            catch (Exception exception)
            {
                System.out.println("\nYou have not entered a Number !!!");
            }

            /* The Reason why one Switch is not for all Options is because options like Initialize Flow and Insert Flow
             * is because for options 1 it does not matter if the Network Flow is created, for option 2 it does not matter
             * if there is not
             */
            if (option == 1)

                initializeFlow();

            else if (option == 8)
            {
                System.out.println("Thank You for using the Network Flow Application !");
                run = false;
                break;
            }
            else if (networkFlow != null)
            {
                // Switch Case Block that calls the respective Method based on the given Option
                switch (option)
                {
                    case 2:

                        findMaxFlow();
                        break;

                    case 3:

                        insertEdge();
                        break;

                    case 4:

                        deleteEdge();
                        break;

                    case 5:

                        editEdgeCapacity();
                        break;

                    case 6:

                        viewNetworkFlowCLI();
                        break;

                    case 7:

                        viewNetworkFlowGUI();
                        break;
                }
            }
            else

                System.out.println("The Network Flow has not been Initialized !!!");

            System.out.println("\n-------------------------------------------------------------------------------");
        }
    }

    /**
     * This Method is used to Initialize the Network Flow by Reading the Data stored in the file that contains
     * the number of nodes and the vertex, vertex and capacity of each flow in the network
     *
     * @throws FileNotFoundException - Throwing a FileNotFoundException in-case the file is not found
     */
    private static void initializeFlow() throws FileNotFoundException
    {
        /* Here all the files that are located within the 'Data Files' Folder are being stored in the files array by
         * using the listFiles() Method in the File Object. Then the User can select an option correspondent to the file
         * he or she wishes to load
         */
        File[] files = new File("Data Files/").listFiles();
        assert files != null;

        System.out.println();

        for ( int count = 0; count < files.length; count ++ )

            System.out.println(files[count].getName() + "<~" + (count + 1));

        System.out.print("\nFile to be Loaded: ");
        String selectedOption = INPUT.next();
        int option;

        // Try Catch Block inorder to Validate Input that is not an Integer
        try
        {
            option = Integer.parseInt(selectedOption);

            // Printing Error Message if Option is not in range of the available options
            if ( ( option < 1 ) || ( option > ( files.length ) ) )
            {
                System.out.println("\nThe Option you have entered is Invalid !!!");
                return;
            }
        }
        catch (Exception exception)
        {
            System.out.println("\nYou have not entered a Number !!!");
            return;
        }

        Arrays.sort(files);

        FileReader fileReader = new FileReader(files[option - 1]);
        Scanner scanner = new Scanner(fileReader);

        int numberOfNodes = Integer.parseInt(scanner.next());

        // The Source is the first Vertex of the first Edge and the Target is the second vertex of the last Edge
        networkFlow = new NetworkFlow(0, numberOfNodes - 1, numberOfNodes);

        networkFlow.setVertices(new Vertex[numberOfNodes]);

        for (int vertexCount = 0; vertexCount < numberOfNodes; vertexCount++)
        {
            Vertex vertex = new Vertex(vertexCount);

            Vertex[] vertices = networkFlow.getVertices();
            vertices[vertexCount] = vertex;

            networkFlow.setVertices(vertices);
        }

        /* Using an ArrayList to keep adding all 3 Attributes required by the Flow Object in the file. This is done
         * by using the hasNext() Method of the Scanner Object that allows us to read data using the next() method
         * till there is nothing left
         */
        while (scanner.hasNext())
        {
            networkFlow.addEdgeToAdjacencyList(Integer.parseInt(scanner.next()), Integer.parseInt((scanner.next())),
                    Integer.parseInt(scanner.next()));
        }
    }

    /**
     * This Method can be used by the User to insert an Edge to the Network Flow to any of the existing Vertices
     */
    private static void insertEdge()
    {

        System.out.print("\nVertex One in Edge to Insert: ");
        String vertexOneInEdge = INPUT.next();

        System.out.print("Vertex Two in Edge to Insert: ");
        String vertexTwoInEdge = INPUT.next();

        System.out.print("Capacity of Edge to Insert: ");
        String capacityInEdge = INPUT.next();

        int vertexOne;
        int vertexTwo;
        int capacity;

        // Try Catch Block inorder to Validate Input that is not an Integer
        try
        {
            vertexOne = Integer.parseInt(vertexOneInEdge);
            vertexTwo = Integer.parseInt(vertexTwoInEdge);
            capacity = Integer.parseInt(capacityInEdge);

            // Printing Error Message if Option is not in range of the available options
            if ( ( vertexOne < networkFlow.getSource() ) || ( vertexTwo > networkFlow.getTarget() ) )

                System.out.println("\nThe Edge you have given is Invalid !!!");

            else if ( vertexOne > vertexTwo )

                System.out.println("\nYou cannot Insert an Inverted Edge !!!");

            else if (capacity < 1)

                System.out.println("\nThe Capacity you have given is less than 1 !!! ");

            else
            {
                boolean edgeExists = false;

                for (Vertex vertex : networkFlow.getVertices())
                {
                    // Not Using an Enhanced For Loop as when an Edge is removed an error occurs, this was not faced in an Iteration Loop
                    for (int adjacentEdgeCount = 0; adjacentEdgeCount < vertex.getAdjacentEdges().size() ; adjacentEdgeCount ++) {

                        Edge edge = vertex.getAdjacentEdges().get(adjacentEdgeCount);

                        if ( ( ( edge.getVertexOne() == vertexOne ) && ( edge.getVertexTwo() == vertexTwo ) ) )
                        {
                            edgeExists = true;
                            System.out.println("\nThe Edge already exists !!!");
                            break;
                        }
                    }
                }

                if (! edgeExists)
                {
                    Vertex[] vertices = networkFlow.getVertices();
                    vertices[vertexOne].insertEdge(new Edge(vertexOne, vertexTwo, capacity));

                    System.out.println("\nThe Edge was Successfully Inserted !!! ");
                }
            }

        }
        catch (Exception exception)
        {
            System.out.println("\nYou have not entered a Number !!!");
        }
    }

    /**
     * This Method deletes a Flow from a Network based on User Input
     */
    private static void deleteEdge()
    {
        System.out.print("\nVertex One in Edge to Delete: ");
        String vertexOneInEdge = INPUT.next();

        System.out.print("Vertex Two in Edge to Delete: ");
        String vertexTwoInEdge = INPUT.next();

        int vertexOne;
        int vertexTwo;

        // Try Catch Block inorder to Validate Input that is not an Integer
        try
        {
            vertexOne = Integer.parseInt(vertexOneInEdge);
            vertexTwo = Integer.parseInt(vertexTwoInEdge);


            // Printing Error Message if Option is not in range of the available options
            if ( ( vertexOne < networkFlow.getSource() ) || ( vertexTwo > networkFlow.getTarget() ) )

                System.out.println("\nThe Edge you have given is Invalid !!!");

            else
            {
                boolean edgeRemoved = false;

                for (Vertex vertex : networkFlow.getVertices())
                {
                    // Not Using an Enhanced For Loop as when an Edge is removed an error occurs, this was not faced in an Iteration Loop
                    for (int adjacentEdgeCount = 0; adjacentEdgeCount < vertex.getAdjacentEdges().size() ; adjacentEdgeCount ++) {

                        Edge edge = vertex.getAdjacentEdges().get(adjacentEdgeCount);

                        if ( ( ( edge.getVertexOne() == vertexOne ) && ( edge.getVertexTwo() == vertexTwo ) ) )
                        {
                            vertex.removeEdge(edge);
                            edgeRemoved = true;
                            System.out.println("\nThe Edge has been Successfully removed !");
                            break;
                        }
                    }
                }

                if (! edgeRemoved)

                    System.out.println("\nThe Edge was not Found !!! ");
            }

        }
        catch (Exception exception)
        {
            System.out.println("\nYou have not entered a Number !!!");
        }
    }

    /**
     * This Method edits the Capacity of an Edge in other words you can alter the maximum amount of flow an Edge can handle
     */
    private static void editEdgeCapacity()
    {
        System.out.print("\nVertex One in Edge to Modify: ");
        String vertexOneInEdge = INPUT.next();

        System.out.print("Vertex Two in Edge to Modify: ");
        String vertexTwoInEdge = INPUT.next();

        System.out.print("Updated Capacity: ");
        String updatedCapacity = INPUT.next();

        int vertexOne;
        int vertexTwo;
        int capacity;

        // Try Catch Block inorder to Validate Input that is not an Integer
        try
        {
            vertexOne = Integer.parseInt(vertexOneInEdge);
            vertexTwo = Integer.parseInt(vertexTwoInEdge);
            capacity = Integer.parseInt(updatedCapacity);

            // Printing Error Message if Option is not in range of the available options
            if ( ( vertexOne < networkFlow.getSource() ) || ( vertexTwo > networkFlow.getTarget() ) )

                System.out.println("\nThe Edge you have given is Invalid !!!");

            else if (capacity < 1)

                System.out.println("\nThe Capacity you have given is less than 1 !!! ");

            else
            {
                boolean edgeEdited = false;

                for (Vertex vertex : networkFlow.getVertices())
                {
                    // Not Using an Enhanced For Loop as when an Edge is removed an error occurs, this was not faced in an Iteration Loop
                    for (int adjacentEdgeCount = 0; adjacentEdgeCount < vertex.getAdjacentEdges().size() ; adjacentEdgeCount ++) {

                        Edge edge = vertex.getAdjacentEdges().get(adjacentEdgeCount);

                        if ( ( ( edge.getVertexOne() == vertexOne ) && ( edge.getVertexTwo() == vertexTwo ) ) )
                        {
                            vertex.editEdge(edge, capacity);
                            edgeEdited = true;
                            System.out.println("\nThe Edge has been Successfully edited !");
                            break;
                        }
                    }
                }

                if (! edgeEdited)

                    System.out.println("\nThe Edge was not Found !!! ");
            }

        }
        catch (Exception exception)
        {
            System.out.println("\nYou have not entered a Number !!!");
        }
    }

    /**
     * This Method uses the getMaxFlow() Method from the NetworkFlow Object to find the Maximum Flow of the Network
     * Flow, and in addition to this the Elapsed Time is shown in Nano Seconds
     */
    private static void findMaxFlow()
    {
        // If the Max Flow is already found it will not be equals to its default value as it cannot be a Negative Number
        if (networkFlow.getMaxFlow() == -1)
        {
            int maxFlow = networkFlow.findMaxFlow();
            System.out.println("Maximum Flow: " + maxFlow);
        }
        else

            System.out.println("Maximum Flow: " + networkFlow.getMaxFlow());
    }

    /**
     * This Method can be used for the User to view all the Edges in the Network Flow for all Vertexes in order without
     * Reversed Edges in CLI
     */
    private static void viewNetworkFlowCLI()
    {
        int edgeCount = 0;
        for (Vertex vertex : networkFlow.getVertices())

            for (Edge edge : vertex.getAdjacentEdges())

                if (edge.getVertexTwo() > edge.getVertexOne())
                {
                    edgeCount ++;
                    System.out.println(edgeCount + " " +  edge);
                }

    }

    /**
     * This Method can be used for the User to view all the Edges in the Network Flow for all Vertexes in order without
     * Reversed Edges in GUI
     */
    private static void viewNetworkFlowGUI()
    {
        GraphGUI graphGUI = new GraphGUI();

        // Adding all Vertices to the Graph GUI
        for (Vertex vertex : networkFlow.getVertices())

            graphGUI.addVertex(String.valueOf(vertex.getVertexName()));

        // Adding all Edges to the Graph GUI
        for (Vertex vertex : networkFlow.getVertices())

            for (Edge edge : vertex.getAdjacentEdges())

                if (edge.getVertexTwo() > edge.getVertexOne())

                    graphGUI.addEdge(String.valueOf(edge.getVertexOne()), String.valueOf(edge.getVertexTwo())
                            , edge.getCapacity(), edge.getFlow());

        graphGUI.display();
    }
}