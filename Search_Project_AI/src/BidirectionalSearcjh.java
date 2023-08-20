

import javax.xml.transform.Result;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Enumeration ;

public class BidirectionalSearch {

    // Stack to store the result path
    public static Stack<State> Result = new Stack<State>();

    // Method to perform bidirectional search
    public static void BidirectionalSearch(State initialState, State goal) {
        // Frontier and explored sets for the forward search
        Queue<State> frontier1 = new LinkedList<State>();
        Hashtable<String, Boolean> inFrontier1 = new Hashtable<>();
        Hashtable<String, Boolean> explored1 = new Hashtable<>();

        // Frontier and explored sets for the backward search
        Queue<State> frontier2 = new LinkedList<State>();
        Hashtable<String, Boolean> inFrontier2 = new Hashtable<>();
        Hashtable<String, Boolean> explored2 = new Hashtable<>();

        // Add the initial state to the forward frontier
        frontier1.add(initialState);
        inFrontier1.put(initialState.hash(), true);

        // Add the goal state to the backward frontier
        frontier2.add(goal);
        inFrontier2.put(goal.hash(), true);

        // Variable to store the intersection state
        int intersection = -1;

        // Perform the bidirectional search
        while (!frontier1.isEmpty() && !frontier2.isEmpty() && intersection == -1) {
            // Perform BFS search on the forward frontier
            BFSsearch(frontier1, inFrontier1, explored1);

            // Perform BFS search on the backward frontier
            BFSsearch(frontier2, inFrontier2, explored2);

            // Check if there is an intersection between the frontiers
            boolean intersectionFound = false;
            String intersectionKey = "";
            Enumeration<String> enumeration = inFrontier1.keys();
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                if (inFrontier2.containsKey(key)) {
                    intersectionKey = key;
                    intersectionFound = true;
                    break;
                }
            }

            // If there is an intersection, construct the result path
            if (intersectionFound) {
                // Construct the path from the goal to the intersection
                int frontier2Size = frontier2.size();
                for (int i = 0; i < frontier2Size; i++) {
                    State state1 = frontier2.poll();
                    if (state1.hash().equals(intersectionKey)) {
                        Stack<State> firstResult = new Stack<State>();
                        while (true) {
                            firstResult.add(state1);
                            if (state1.getParentState() == null) {
                                break;
                            } else {
                                state1 = state1.getParentState();
                            }
                        }
                        int firstResultSize = firstResult.size();
                        for (int j = 0; j < firstResultSize; j++) {
                            Result.add(firstResult.pop());
                        }
                        break;
                    }
                }

                // Construct the path from the intersection to the initial state
                int frontier1Size = frontier1.size();
                for (int i = 0; i < frontier1Size; i++) {
                    State state1 = frontier1.poll();
                    if (state1.hash().equals(intersectionKey)) {
                        state1 = state1.getParentState();
                        while (true) {
                            Result.add(state1);
                            if (state1.getParentState() == null) {
                                break;
                            } else {
                                state1 = state1.getParentState();
                            }
                        }
                        break;
                    }
                }

                // Output the result
                result();
                break;
            }
        }
    }

    // Method to perform BFS search
    public static void BFSsearch(Queue<State> frontier, Hashtable<String, Boolean> inFrontier,
            Hashtable<String, Boolean> explored) {
        State tempState = frontier.poll();
        inFrontier.remove(tempState.hash());
        explored.put(tempState.hash(), true);
        ArrayList<State> children = tempState.successor();
        for (int i = 0; i < children.size(); i++) {
            if (!(inFrontier.containsKey(children.get(i).hash()))
                    && !(explored.containsKey(children.get(i).hash()))) {
                frontier.add(children.get(i));
                inFrontier.put(children.get(i).hash(), true);
            }
        }
    }

    // Method to output the result path
    private static void result() {
        try {
            FileWriter myWriter = new FileWriter("BidirectionalResult.txt");
            System.out.println("initial state : ");
            while (!Result.empty()) {
                State tempState = Result.pop();
                if (tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();

                myWriter.write(tempState.getSelectedNodeId() + " ,");
                myWriter.write(tempState.outputGenerator() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}




