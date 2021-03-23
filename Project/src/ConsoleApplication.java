import java.io.FileNotFoundException;
import java.io.FileReader;
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
                            "02: Find the Maximum Flow\n" +
                            "03: Delete a Flow\n" +
                            "04: Edit the Capacity of a Flow\n" +
                            "05: View the Network Flow\n" +
                            "06: Quit Program\n\n" +
                            "Desired Option: ");

            String selectedOption = INPUT.next();
            int option = 0;

            // Try Catch Block inorder to Validate Input that is not an Integer
            try
            {
                option = Integer.parseInt(selectedOption);

                // Printing Error Message if Option is not in range of the available options
                if (option < 1 || option > 6)
                {
                    System.out.println("\nThe Option you have entered is Invalid !!!");
                    continue;
                }
            }
            catch (Exception exception)
            {
                System.out.println("\nYou have not entered a Number !!!");
            }

            // Enhanced Switch Case Block that calls the respective Method based on the given Option
            switch (option) {
                case 1 -> initializeFlow();
                case 2 -> findMaxFlow();
                case 3 -> deleteFlow();
                case 4 -> editFlowCapacity();
                case 5 -> viewNetworkFlow();
                case 6 -> run = false;
            }
            System.out.println("\n-------------------------------------------------------------------------------");
        }
    }

    /**
     * This Method is used to Initialize the Network Flow by Reading the Data stored in the file 'data.txt' that contains
     * the number of nodes and the vertex, edge and capacity of each flow in the network
     *
     * @throws FileNotFoundException - Throwing a FileNotFoundException in-case the 'data.txt' file is not found
     */
    private static void initializeFlow() throws FileNotFoundException
    {
        FileReader fileReader = new FileReader("data.txt");
        Scanner scanner = new Scanner(fileReader);

        int numberOfNodes = Integer.parseInt(scanner.next());

        Flow[] flows = new Flow[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i ++)
        {
            flows[i] = new Flow(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next()),
                    Integer.parseInt(scanner.next()));
        }

        networkFlow = new NetworkFlow(flows[0].getVertex(), flows[numberOfNodes - 1].getEdge(), numberOfNodes, flows);

        System.out.println("Network Flow: " + networkFlow);
    }

    private static void insertFlow()
    {

    }

    /**
     * This Method deletes a Flow from a Network based on User Input
     */
    private static void deleteFlow()
    {
        if (networkFlow != null)
        {
            int flowCount = 0;
            for (Flow flow : networkFlow.getFlows())
            {
                System.out.println(flow + " <~ " + flowCount++);
            }

            System.out.print("\nWhich Flow do You Wish to Remove (Number Based on the Displayed Flows): ");
            String selectedFlowToDelete = INPUT.next();

            // Try Catch Block inorder to Validate Input that is not an Integer
            try {
                int flowToDelete = Integer.parseInt(selectedFlowToDelete);

                // Printing Error Message if Option is not in range of the available Flows
                if (flowToDelete < 1 || flowToDelete > networkFlow.getNumberOfNodes())
                {
                    System.out.println("\nThe Option you have entered is Invalid !!!");
                }
                else
                {
                    /* Creating a new Flow Array without the Flow the User wishes to remove using an If Condition nested
                     * in the below For Loop to ignore the index of the removed flow
                     */
                    Flow[] updatedFlows = new Flow[networkFlow.getNumberOfNodes() - 1];

                    int index = 0;
                    for (Flow flow : networkFlow.getFlows())
                    {
                        if (index == (flowToDelete - 1))
                        {
                            index++;
                            continue;
                        }
                        updatedFlows[index] = flow;
                        index++;
                    }

                    networkFlow.setFlows(updatedFlows);
                    networkFlow.setNumberOfNodes(networkFlow.getNumberOfNodes() - 1);
                }
            }
            catch (Exception exception)
            {
                System.out.println("\nYou have not entered a Number !!!");
            }
        }
        else System.out.println("The Network Flow has not been Initialized !!!");
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
