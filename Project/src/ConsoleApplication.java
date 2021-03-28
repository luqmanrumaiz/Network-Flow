import java.io.*;
import java.util.*;

public class ConsoleApplication
{
    private static NetworkFlow networkFlow;
    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("0oo - Welcome to the Network Flow Application - ooO");

        boolean run = true;
        while (run)
        {
            System.out.print(
                    "\nHere below are the Options you can Execute,\n" +
                            "01: Initialize the Flow\n" +
                            "02: Insert a Flow\n" +
                            "03: Find the Maximum Flow\n" +
                            "04: Delete a Flow\n" +
                            "05: Edit the Capacity of a Flow\n" +
                            "06: View the Network Flow\n" +
                            "07: Quit Program\n\n" +
                            "Desired Option: ");

            String selectedOption = INPUT.next();
            int option = 0;

            // Try Catch Block inorder to Validate Input that is not an Integer
            try
            {
                option = Integer.parseInt(selectedOption);

                // Printing Error Message if Option is not in range of the available options
                if (option < 1 || option > 7)
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

            if (option == 2 && networkFlow != null)

                insertFlow();

            else if (networkFlow != null)
            {
                // Enhanced Switch Case Block that calls the respective Method based on the given Option
                switch (option)
                {
                    case 3 -> findMaxFlow();
                    case 4 -> deleteFlow();
                    case 5 -> editFlowCapacity();
                    case 6 -> viewNetworkFlow();
                    case 7 -> {
                        System.out.println("Thank You for using the Network Flow Application !");
                        run = false;
                    }
                }
            }
            else if (networkFlow == null)

                System.out.println("The Network Flow has not been Initialized !!!");

            else if (networkFlow.getEdges().length == 0)

                System.out.println("There are no Flows to Delete in the Network Flow");



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

        for ( int count = 1; count < files.length; count ++ )

            System.out.println(files[count].getName() + "<~" + count);

        System.out.print("\nFile to be Loaded: ");
        String selectedOption = INPUT.next();
        int option;

        // Try Catch Block inorder to Validate Input that is not an Integer
        try
        {
            option = Integer.parseInt(selectedOption);

            // Printing Error Message if Option is not in range of the available options
            if ( ( option < 1 ) || ( option > ( files.length + 1 ) ) )
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

        FileReader fileReader = new FileReader(files[option]);
        Scanner scanner = new Scanner(fileReader);

        int numberOfNodes = Integer.parseInt(scanner.next());

        /* Using an ArrayList to keep adding all 3 Attributes required by the Flow Object in the file. This is done
         * by using the hasNext() Method of the Scanner Object that allows us to read data using the next() method
         * till there is nothing left
         */
        ArrayList<Edge> edges = new ArrayList<>();

        while (scanner.hasNext())

            edges.add(new Edge(new Vertex(scanner.next()), new Vertex(scanner.next()), Integer.parseInt(scanner.next())));

        // The Source is the first Vertex of the first Edge and the Target is the second vertex of the last Edge
        networkFlow = new NetworkFlow(edges.get(0).getVertexOne().getVertexName(),
                edges.get(numberOfNodes - 1).getVertexTwo().getVertexName(), numberOfNodes, edges.toArray(new Edge[0]));

        System.out.println(networkFlow);
    }

    private static void insertFlow()
    {

    }

    /**
     * This Method deletes a Flow from a Network based on User Input
     */
    private static void deleteFlow()
    {

    }

    private static void searchFlow()
    {

    }

    private static void editFlowCapacity()
    {
    }

    private static void findMaxFlow()
    {

    }

    private static void viewNetworkFlow()
    {

    }
}