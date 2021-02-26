package smart;

public class SmartSudoku {

    public SmartSudoku() {

    }

    public boolean solve(Board b){
        System.out.println(b.toString());
        if(b.solve()){
            return true;
        }

        int[][] candidates = b.getCandidates();
        int minLenCandiadteIdx=-1;

        for (int i = 0; i < 81; i++) {
            if (isOnlyOneCandidate(candidates[i])) {
                continue;
            }
            if(minLenCandiadteIdx==-1){
                minLenCandiadteIdx=i;
            }
            if (candidates[i].length < candidates[minLenCandiadteIdx].length) {
                minLenCandiadteIdx = i;
            }
        }

        if(isOnlyOneCandidate(candidates[minLenCandiadteIdx])){
            return false;
        }

        for (int i = 0; i < 9; i++) {
            if(candidates[minLenCandiadteIdx][i]==0){
                continue;
            }
            int[] nextNumbers=new int[81];
            System.arraycopy(b.getNumbers(), 0, nextNumbers, 0, 81);
            nextNumbers[minLenCandiadteIdx] = i+1;

            int row = minLenCandiadteIdx / 9;
            int column = minLenCandiadteIdx % 9;
            System.out.println("###########################################################");
            System.out.println(String.format("Search in row %d column %d with %d", row, column, nextNumbers[minLenCandiadteIdx]));

            Board nextBoard = new Board(nextNumbers);
            if(solve(nextBoard)){
                return true;
            }
        }
        return false;
    }

    private boolean isOnlyOneCandidate(int[] candidate){
        int count=0;
        for (int i = 0; i < 9; i++) {
            if(candidate[i]>0){
                count++;
            }
        }
        return count==1;
    }
}
