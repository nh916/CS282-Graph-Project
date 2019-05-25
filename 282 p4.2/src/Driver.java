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
                    graph.addEdge(i, neighbor, cost);
                }
            }


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
//                    System.out.println(graph.verify());
//                    System.out.println(graph.bfs(0));
//                    System.out.println(graph.isConnected());
                    if (graph.isConnected()) {
                        System.out.println("graph is connected");
                    } else {
                        System.out.println("graph is disconnected");
                    }
                    break;
                }
                case (2): {

                    graph.primMST();
                    break;
                }
                case (3): {
                    try {
                        System.out.println("start?");
                        graph.dijkstra(graph.matrix, input.nextInt());
                        break;
                    } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                        System.out.println("not a valid choice");
                        inCaseOfError();
                    }
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
        try {
            Driver driver = new Driver();
            driver.readFromFileAndAddToGraph("text.txt");
            driver.options();
        } catch (Exception e) {
            System.out.println("EXCEPTION!");
        }
    }
}