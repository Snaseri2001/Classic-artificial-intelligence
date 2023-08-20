import java.io.FileWriter;
import java.io.IOException;
import java.util.* ;
import java.util.ArrayList ;
import java.lang.* ;

 public class DFS {
    public static void Search(State initialState){
        Stack<State> frontier = new Stack<State>(); // Create a stack to store the frontier
        Hashtable<String, Boolean> inFrontier = new Hashtable<>(); // Create a hashtable to keep track of states in the frontier
        Hashtable<String, Boolean> explored = new Hashtable<>(); // Create a hashtable to keep track of explored states

        if(isGoal(initialState)){ // Check if the initial state is the goal state
            result(initialState); // If it is, call the result() method with the initial state
            return;
        }

        frontier.add(initialState); // Add the initial state to the frontier
        inFrontier.put(initialState.hash(),true); // Mark the initial state as in the frontier

        while (!frontier.isEmpty()){ // Loop until the frontier is empty
            State tempState = frontier.pop(); // Pop a state from the frontier
            inFrontier.remove(tempState.hash()); // Remove the state from the frontier
            explored.put(tempState.hash(),true); // Mark the state as explored

            ArrayList<State> children = tempState.successor(); // Get the successor states of the current state

            for(int i = 0;i<children.size();i++){ // Iterate through the successor states
                if(!(inFrontier.containsKey(children.get(i).hash())) // Check if the state is not in the frontier
                        && !(explored.containsKey(children.get(i).hash()))) { // Check if the state is not explored

                    if (isGoal(children.get(i))) { // Check if the state is the goal state
                        result(children.get(i)); // If it is, call the result() method with the state
                        return;
                    }

                    frontier.add(children.get(i)); // Add the state to the frontier
                    inFrontier.put(children.get(i).hash(), true); // Mark the state as in the frontier
                }
            }
        }
    }

    private static boolean isGoal(State state){
        for (int i = 0; i < state.getGraph().size(); i++) { // Iterate through the nodes in the graph
            if(state.getGraph().getNode(i).getColor() == Color.Red // Check if the node color is red or black
                    || state.getGraph().getNode(i).getColor() == Color.Black){
                return false; // If any node is red or black, it is not the goal state
            }
        }
        return true; // If all nodes are not red or black, it is the goal state
    }

    private static void result(State state){
        Stack<State>  states = new Stack<State>(); // Create a stack to store the states in the path
        while (true){
            states.push(state); // Push the current state to the stack
            if(state.getParentState() == null){ // Check if the current state has no parent state
                break; // If it doesn't, break the loop
            }
            else {
                state = state.getParentState(); // Set the current state to its parent state
            }
        }

        try {
            FileWriter myWriter = new FileWriter("DfsResult.txt"); // Create a FileWriter to write the result to a file
            System.out.println("initial state : ");
            while (!states.empty()){ // Loop until the stack is empty
                State tempState = states.pop(); // Pop a state from the stack
                if(tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();

                myWriter.write(tempState.getSelectedNodeId()+" ,"); // Write the selected node ID to the file
                myWriter.write(tempState.outputGenerator()+"\n"); // Write the output generator to the file
            }
            myWriter.close(); // Close the FileWriter
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
