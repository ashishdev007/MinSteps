
public class MinSteps {

    private int tokens;
    private int[] steps;
    private int[] moves;

    public MinSteps(int tokens){
        this.tokens = tokens;
        this.steps = new int[tokens +1];
        this.moves = new int[tokens];

        dymanicSolution();
    }

    private void dymanicSolution(){

        steps[0] = 0;
        moves[0] = 1;

        for (int i = 1; i < tokens; i++) {

            int min = steps[i-1];
            int minIndex = i-1;

            if ((i+1)%2 == 0){
                if (steps[((i+1)/2) - 1] < min){
                    min = steps[((i+1)/2) - 1];
                    minIndex = ((i+1)/2) - 1;
                }
            }

            if (((i+1)%3) ==0){
                if (steps[((i+1)/3) - 1] < min){
                    min = steps[((i+1)/3) - 1];
                    minIndex = ((i+1)/3) - 1;
                }
            }

            steps[i] = 1 + min;
            moves[i] = minIndex;

        }

    }

    public int dpSolution(){

        return steps[tokens - 1];
    }

    public int recSolution(){
        return recSolution(tokens - 1);
    }

    public int recSolution(int tok){

        if (tok == 0){
            return 0;
        }
        else if (tok == tokens){
            return Integer.MAX_VALUE;
        }

        int by2 = tokens;
        int by3 = tokens;

        if ((tok + 1) % 2 == 0){
            by2 = ((tok+1)/2) - 1;
        }

        if ((tok + 1) % 3 == 0){
            by2 = ((tok+1)/3) - 1;
        }

        return (1 + Math.min(recSolution(tok - 1), Math.min(recSolution(by2), recSolution(by3))));
    }

    public String getMoves(){

        StringBuilder display = new StringBuilder();

        display.append(tokens);

        if (tokens != 1){
            display.append(" --> ");
        }

        int i = tokens - 1;

        while (i > 0){
            display.append((moves[i] + 1));

            if ((moves[i] + 1) != 1){
                display.append(" --> ");
            }

            i = moves[i];

        }

        return display.toString();
    }

}
