import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class AStar {

    // This method performs the A* search algorithm
    public static void Search(State initialState) {
        // Comparator to compare states based on their A* values
        Comparator<State> StateComparator = new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                if (s1.AStar >= s2.AStar) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        // Priority queue to store states based on their A* values
        PriorityQueue<State> Frontier = new PriorityQueue<State>(StateComparator);
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();

        // Check if the initial state is the goal state
        if (isGoal(initialState)) {
            result(initialState);
            return;
        }

        // Set the cost and A* value of the initial state
        initialState.CostOFThisStateForAStar = 0;
        initialState.AStar = initialState.getHuristic();

        // Add the initial state to the frontier
        Frontier.add(initialState);
        inFrontier.put(initialState.hash(), true);

        // Explore the graph using A* search
        while (!Frontier.isEmpty()) {
            State tempState = Frontier.poll();

            // Check if the state has not been explored
            if (!(explored.containsKey(tempState.hash()))) {
                explored.put(tempState.hash(), true);
                ArrayList<State> children = tempState.successorAstar();

                // Check if the goal state is found
                if (isGoal(tempState)) {
                    result(tempState);
                    return;
                }

                // Add the children states to the frontier
                for (int i = 0; i < children.size(); i++) {
                    if (!(explored.containsKey(children.get(i).hash()))) {
                        Frontier.add(children.get(i));
                    }
                }
            }
        }
    }

    // This method checks if a given state is the goal state
    private static boolean isGoal(State state) {
        // Implementation of the goal state condition
    }

    // This method prints the result of the search
    private static void result(State state) {
        // Implementation of the result printing
    }
}

    }
private static boolean isGoal(State state) {
    // Check if the state is the goal state
    for (int i = 0; i < state.getGraph().size(); i++) {
        if (state.getGraph().getNode(i).getColor() == Color.Red
                || state.getGraph().getNode(i).getColor() == Color.Black) {
            return false;
        }
    }
    return true;
}

private static void result(State state) {
    // Store the path to the goal state in a stack
    Stack<State> states = new Stack<State>();
    while (true) {
        states.push(state);
        if (state.getParentState() == null) {
            break;
        } else {
            state = state.getParentState();
        }
    }

    // Write the result to a file
    try {
        FileWriter myWriter = new FileWriter("AStarResult.txt");
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
