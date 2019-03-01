
/**
* Developer: Ashish Dev
* CWID: 30100886
* Description: The class helps to optimize The Game of Tokens. Given n tokens, we have to get to 1 token. We can remove one token,
* n/2 tokens (if n%2 == 0) or n/3 tokens (if n%3 == 0). 
* The program analyzes the total numbers of steps required to get from the
* given number of tokens to one token and gives a trace of the best steps to take.
*/
public class MinSteps {

    /**
     * Variable to store the initial no of tokens in the game
     */
    private int tokens;
    /**
     * Array to store the number of moves associated with each number of tokens. The index represents the number of tokens
     * being dealt with. (Ignore index 0)
     */
    private int[] steps;
    /**
     * Array to store the next step associated with the present step. The index represents the number of tokens at hand.
     * (Ignore index 0)
     */
    private int[] moves;

    /**
     * Intializes the instance variables and calls the dynamicSolution method
     * @param tokens The number of tokens at the start of the game
     */
    public MinSteps(int tokens){
        this.tokens = tokens;
        this.steps = new int[tokens + 1];
        this.moves = new int[tokens + 1];

        dymanicSolution();
    }

    /**
     * Method that uses dynamic programming to assign the number of steps associated with the number of tokens. The no of steps calculated are
     * stored in "steps". Also stores the next steps associated with each no of token in "moves".
     */
    private void dymanicSolution(){

        // If there is only one token, number of steps is zero.
        steps[1] = 0;
        // If there is only one token, you have only one step ie "1".
        moves[1] = 1;

        // Start calculating the number of steps from 2 to the given number of tokens.
        for (int i = 2; i <= tokens; i++) {

            // Calculate the minimum of steps[n-1], steps[n/2] (if exists) and steps[n/3] (if exists)
            int min = steps[i-1];
            int minIndex = i-1;

            if ((i % 2) == 0){

                if (steps[ i / 2] < min){
                    min = steps[i / 2];
                    minIndex = i / 2;
                }

            }

            if ((i % 3) == 0){

                if (steps[i / 3] < min){
                    min = steps[i / 3];
                    minIndex = i / 3;
                }

            }

            // Add 1 to the minimum and store it as the steps required for the given steps.
            steps[i] = 1 + min;

            // Store the index with the minimum value for tracing
            moves[i] = minIndex;

        }

    }

    /**
     * Method to return the total number of steps required in the game by accessing "steps". (The actual calculation happens in "dymanicSolution" method)
     * @return The total number of steps
     */
    public int dpSolution(){
        return steps[tokens];
    }

    /**
     * Uses recursion to calculate the total number of steps associated with the number of tokens
     * @param tok The number of tokens
     * @return The number of steps
     */
    private int recSolution(int tok){

        // Steps == 0 if no. of tokens == 1
        if (tok == 1){
            return 0;
        }

        // If the tokens passed is the flag number i.e 0, return the max integer
        else if (tok == 0){
            return Integer.MAX_VALUE;
        }

        // variables to store the n/2 and n/3 indices if they are possible.
        // Initialize them with the flag index i.e 0
        int by2 = 0;
        int by3 = 0;

        // If n/2 is possible, update by2 to proper index
        if (tok % 2 == 0){
            by2 = tok / 2;
        }

        // If n/3 is possible, update by3 to proper index
        if (tok % 3 == 0){
            by2 = tok / 3;
        }

        // Recursive call to n-1, n/2 and n/3
        // Pick the minimum value and add 1.
        return (1 + Math.min(recSolution(tok - 1), Math.min(recSolution(by2), recSolution(by3))));
    }

    /**
     * Calls the overloading recSolution method to calculate the total number of steps in the game
     * @return The total no of steps
     */
    public int recSolution(){
        return recSolution(tokens);
    }

    /**
     * Method to show the exact steps of the game by using the values stored in "moves"
     * @return The trace of the steps
     */
    public String getMoves(){

        // variable to store the trace
        StringBuilder display = new StringBuilder();

        // Append the first step i.e: the tokens at the beginning
        display.append(tokens);

        if (tokens != 1){
            display.append(" --> ");
        }

        // loop until you are at the step where you have only one token
        int i = tokens;

        while (i > 1){

            // Append the next move associated with the given no of tokens
            display.append(moves[i]);

            if (moves[i] != 1){
                display.append(" --> ");
            }

            // update i to the number of tokens in next move
            i = moves[i];

        }

        // return the trace
        return display.toString();
    }

}
