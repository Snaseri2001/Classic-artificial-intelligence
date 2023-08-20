import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class ids {

    // This method performs a breadth-first search on the graph
    static boolean BFS(int depth, State initialState) {
        initialState.depthOfState = 0;
        Queue<State> frontier = new LinkedList<State>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();

        // Check if the initial state is the goal state
        if (isGoal(initialState)) {
            result(initialState);
            return false;
        }

        // Add the initial state to the frontier
        frontier.add(initialState);
        inFrontier.put(initialState.hash(), true);

        // Explore the graph using BFS
        while (!frontier.isEmpty()) {
            State tempState = frontier.poll();
            inFrontier.remove(tempState.hash());
            explored.put(tempState.hash(), true);
            ArrayList<State> children = tempState.successor();

            // Set the depth of each child state
            for (int o = 0; o < children.size(); o++) {
                children.get(o).depthOfState = children.get(o).getParentState().depthOfState + 1;
            }

            // Check if the depth limit has been reached
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).depthOfState > depth) {
                    return false;
                }

                // Add the child state to the frontier if it has not been explored or added to the frontier
                if (!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                    if (isGoal(children.get(i))) {
                        result(children.get(i));
                        return true;
                    }
                    frontier.add(children.get(i));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }
        }
        return false;
    }

    // This method performs a depth-first search on the graph
    public static boolean DFS(int depth, State initialState) {
        initialState.depthOfState = 0;
        Stack<State> Frontier = new Stack<State>();
        LinkedList<String> explored = new LinkedList<>();
        int counter1 = 0;

        // Check if the initial state is the goal state
        if (isGoal(initialState)) {
            result(initialState);
            return true;
        }

        // Add the initial state to the frontier
        Frontier.add(initialState);

        // Explore the graph using DFS
        while (!Frontier.isEmpty()) {
            counter1 += 1;
            State tempState = Frontier.pop();

            // Add the state to the explored list if it is not the initial state
            if (counter > 1) {
                explored.add(tempState.hash());

                // Remove all states after the parent state from the explored list
                State fatherState = tempState.getParentState();
                int fatherStateIndex = explored.indexOf(fatherState.hash());
                if (fatherStateIndex < explored.size() - 1) {
                    for (int y = fatherStateIndex + 1; y < explored.size(); y++) {
                        explored.remove(y);
                    }
                }
            }

            // Add the state to the explored list
            explored.add(tempState.hash());

            // Set the depth of each child state
            ArrayList<State> children = tempState.successor();
            for (int o = 0; o < children.size(); o++) {
                children.get(o).depthOfState = children.get(o).getParentState().depthOfState + 1;
            }

            // Check if the depth limit has been reached
            if (children.get(0).depthOfState <= depth) {
                for (int i = 0; i < children.size(); i++) {
                    // Add the child state to the frontier if it has not been explored
                    if (!(explored.contains(children.get(i).hash()))) {
                        if (isGoal(children.get(i))) {
                            result(children.get(i));
                            return true;
                        }
                        Frontier.add(children.get(i));
                    }
                }
            }
            counter++;
        }
        System.out.println(counter1);
        return false;
    }

    // This method performs the Iterative Deepening Search algorithm
    static void IDS(State initialState) {
        int level = 1;

        // Run DFS with increasing depth limits until the goal state is found or the search space is exhausted
        while (true) {
            System.out.println("level : " + level);
            boolean recieve = DFS(level, initialState);
            if (recieve) {
                return;
            }
            level += 1;
        }
    }

    // This method checks if a given state is the goal state
    private static boolean isGoal(State state) {
        for (int i = 0; i < state.getGraph().size(); i++) {
            if (state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black) {
                return false;
            }
        }
        return true;
    }

    // This method prints the path from the initial state to the goal state
    private static void result(State state) {
        Stack<State> states = new Stack<State>();

        // Push each state onto the stack until the initial state is reached
        while (true) {
            states.push(state);
            if (state.getParentState() == null) {
                break;
            } else {
                state = state.getParentState();
            }
        }

        // Write the path to a file and print the initial state and each state in the path
        try {
            FileWriter myWriter = new FileWriter("IDSResult1.txt");
            System.out.println("initial state : ");
            while (!states.empty()) {
                State tempState = states.pop();
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



