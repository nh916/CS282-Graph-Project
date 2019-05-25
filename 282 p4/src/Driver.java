import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    Graph graph;

    public Driver() {

    }

    //    reads file line by line
    private void readFromFileAndAddToGraph(String file) {
        Scanner scan = null;
        int numberOfNodes;
        try {
            scan = new Scanner(new FileInputStream(file));


            numberOfNodes = scan.nextInt();

            graph = new Graph(numberOfNodes);

            for (int i = 0; i < numberOfNodes; i++) {

//                used to also put in the node in the list as well but now the index of the array is the first node
//                Node vertex = new Node(i, 0);
//                graph.addVertex(i, vertex);

                int numberOfNeighbors = scan.nextInt();

                for (int j = 0; j < numberOfNeighbors; j++) {
                    int neighbor = scan.nextInt();
                    int cost = scan.nextInt();
                    Node vertexNeighbor = new Node(i,neighbor,cost);

                    graph.addNeighbor(i, vertexNeighbor);
                }
            }


            graph.print();


        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (NullPointerException e) {
            System.out.println("file error");
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }


    //    prints the menu
    private void printMenu() {
//        System.out.println("-------------------------------");
        System.out.println("The choices for the menu are:");
        System.out.println("\t1.Is Connected");
        System.out.println("\t2.Minimum Spanning Tree");
        System.out.println("\t3.Shortest Path ");
        System.out.println("\t4.Quit");
    }

    private void inCaseOfError() {
        options();
    }

    private void options() {
        int choice;
        Scanner input = new Scanner(System.in);
        boolean flag = true;

        while (flag) {

//            this could be print out statement but i dont like having a whole bunch of prints. rather put it in a method
            printMenu();
// in case of letter input number exception
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("this is an invalid choice!");
                inCaseOfError();
                break;
            }


            switch (choice) {

                case (1): {
                    System.out.println(graph.connected());
                    break;
                }
                case (2): {
//                    System.out.println(graph.MST());
                    graph.MST();
                    System.out.println();
                    break;
                }
                case (3): {
                    System.out.println("which node do you want to find the shortest path from?");
                    Node start = new Node(input.nextInt(), 0, 0);
                    System.out.println("where would you like to finish?");
                    Node finish = new Node(input.nextInt(), 0, 0);

//                    System.out.println(graph.shortestDist(start, finish));

                    break;
                }

                case (4): {
//                    System.exit(0);
                    flag = false;
                    break;
                }

                default: {
                    System.out.println("Not a valid choice. Please try again.");
                    break;
                }
            }
        }
    }


    public static void main(String[] args) {
        Driver driver = new Driver();
        driver.readFromFileAndAddToGraph("text.txt");
        driver.options();
    }

}