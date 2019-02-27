
public class MinSteps {

    private int tokens;
    private int[] steps;
    private int[] moves;

    public MinSteps(int tokens){
        this.tokens = tokens;
        this.steps = new int[tokens + 1];
        this.moves = new int[tokens + 1];

        dymanicSolution();
    }

    private void dymanicSolution(){

        steps[1] = 0;
        moves[1] = 1;

        for (int i = 2; i <= tokens; i++) {

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

            steps[i] = 1 + min;
            moves[i] = minIndex;

        }

    }

    public int dpSolution(){

        return steps[tokens];
    }

    public int recSolution(){
        return recSolution(tokens);
    }

    public int recSolution(int tok){

        if (tok == 1){
            return 0;
        }
        else if (tok == tokens + 1){
            return Integer.MAX_VALUE;
        }

        int by2 = tokens + 1;
        int by3 = tokens + 1;

        if (tok % 2 == 0){
            by2 = tok / 2;
        }

        if (tok % 3 == 0){
            by2 = tok / 3;
        }

        return (1 + Math.min(recSolution(tok - 1), Math.min(recSolution(by2), recSolution(by3))));
    }

    public String getMoves(){

        StringBuilder display = new StringBuilder();

        display.append(tokens);

        if (tokens != 1){
            display.append(" --> ");
        }

        int i = tokens;

        while (i > 1){
            display.append(moves[i]);

            if (moves[i] != 1){
                display.append(" --> ");
            }

            i = moves[i];

        }

        return display.toString();
    }

}
