package smart;

public class Board {
    private int[][] nums = new int[9][9];
    private int[] numsCount = new int[9];
    private int totalNumsCount;
    private int[] numbers = new int[81];
    private int[][] candidates = new int[81][9];

    public Board(int[] input) {
        System.arraycopy(input, 0, this.numbers, 0, 81);
        for (int i = 0; i < 81; i++) {
            if (this.numbers[i] > 0) {
                this.candidates[i][this.numbers[i]-1]=1;
                totalNumsCount++;
            } else {
                for (int j = 0; j < 9; j++) {
                    this.candidates[i][j] = 1;
                }
            }
        }
        refreshCandidates();
    }

    public void solve() {
        while (this.totalNumsCount < 81) {

            int prevTotalNumberCount = this.totalNumsCount;
            tryPutNumber();
            if (this.totalNumsCount == 81) {
                System.out.println("Solved:");
                System.out.println(this.toString());
                break;
            }
            if (prevTotalNumberCount == this.totalNumsCount) {
                System.out.println("Unable to solve");
                break;
            }
            refreshCandidates();
            System.out.println("############## After one round");
            System.out.println(this.toString());
        }
    }

    private void refreshCandidates() {
        for (int i = 0; i < 81; i++) {
            if (this.numbers[i] > 0) {
                continue;
            }
            int row = i / 9;
            int column = i % 9;
            for (int j = 9 * row; j < 9 * (row + 1); j++) {
                if (this.numbers[j] > 0) {
                    this.candidates[i][this.numbers[j] - 1] = 0;
                }
            }
            for (int j = column; j <= (72 + column); j += 9) {
                if (this.numbers[j] > 0) {
                    this.candidates[i][this.numbers[j] - 1] = 0;
                }
            }
            int blockRow = row / 3;
            int blockColumn = column / 3;
            int start = 27 * blockRow + 3 * blockColumn;
            int count = 0;
            for (int j = 0; j < 9; j++) {
                if (this.numbers[start + j] > 0) {
                    this.candidates[i][this.numbers[start + j] - 1] = 0;
                }
                count++;
                if (count % 3 == 0) {
                    start = start + 6;
                }
            }

        }
    }

    private boolean tryPutNumber() {
        boolean updated = false;
        for (int i = 0; i < 81; i++) {
            if (this.numbers[i] > 0) {
                continue;
            }
            int row = i / 9;
            int column = i % 9;
            System.out.println(String.format("try to find candidate for row %d column %d", row, column));
            int candidate = findOnlyCandidate(i);
            if (candidate > 0) {
                System.out.println(String.format("put %d at row %d column %d", candidate, row, column));
                this.totalNumsCount++;
                this.numbers[i] = candidate;
                updated = true;
            }
        }



        return updated;
    }

    private int findOnlyCandidate(int idx) {
        int count = 0;
        int lastCandidate = 0;
        for (int i = 0; i < 9; i++) {
            if (this.candidates[idx][i] > 0) {
                count++;
                lastCandidate = i+1;
            }
        }
        if (count == 1) {
            System.out.println(String.format("found only candidate %d", lastCandidate));
            return lastCandidate;
        }

        int row = idx / 9;
        int column = idx % 9;
        System.out.println(String.format("try to find only candidate by look at row %d", row));

        int start = 9 * row;
        int[] tmpCandidate=new int[9] ;
        System.arraycopy(this.candidates[idx], 0, tmpCandidate, 0, 9);
        for (int i = start; i < start + 9; i++) {
            if (i == idx) {
                continue;
            }
            tmpCandidate = removeDuplicateCandidate(tmpCandidate, this.candidates[i]);
        }
        count = 0;
        for (int i = 0; i < 9; i++) {
            if (tmpCandidate[i] > 0) {
                count++;
                lastCandidate = i+1;
            }
        }
        if (count == 1) {
            System.out.println(String.format("found only candidate %d", lastCandidate));
            this.candidates[idx]=tmpCandidate;
            return lastCandidate;
        }

        System.out.println(String.format("try to find only candidate by look at column %d", row));
        tmpCandidate=new int[9] ;
        System.arraycopy(this.candidates[idx], 0, tmpCandidate, 0, 9);
        for (int i = column; i < (72 + column); i += 9) {
            if (i == idx) {
                continue;
            }
            tmpCandidate = removeDuplicateCandidate(tmpCandidate, this.candidates[i]);
        }
        count = 0;
        for (int i = 0; i < 9; i++) {
            if (tmpCandidate[i] > 0) {
                count++;
                lastCandidate = i+1;
            }
        }
        if (count == 1) {
            System.out.println(String.format("found only candidate %d", lastCandidate));
            this.candidates[idx]=tmpCandidate;
            return lastCandidate;
        }

        tmpCandidate=new int[9] ;
        System.arraycopy(this.candidates[idx], 0, tmpCandidate, 0, 9);

        int blockRow = row / 3;
        int blockColumn = column / 3;
        System.out.println(String.format("try to find only candidate by look at block %d:%d", row,column));
        start = 27 * blockRow + 3 * blockColumn;
        count = 0;
        for (int j = 0; j < 9; j++) {
            if ((start + j) == idx) {
                continue;
            }
            tmpCandidate = removeDuplicateCandidate(tmpCandidate, this.candidates[j]);
            count++;
            if (count % 3 == 0) {
                start = start + 6;
            }
        }
        count = 0;
        for (int i = 0; i < 9; i++) {
            if (tmpCandidate[i] > 0) {
                count++;
                lastCandidate = i+1;
            }
        }
        if (count == 1) {
            System.out.println(String.format("found only candidate %d", lastCandidate));
            this.candidates[idx]=tmpCandidate;
            return lastCandidate;
        }

        return 0;
    }

    private int[] removeDuplicateCandidate(int[] source, int[] target) {
        for (int i = 0; i < 9; i++) {
            if (source[i]==1&&source[i] == target[i]) {
                source[i] = 0;
            }
        }
        return source;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
       /* sb.append("numsCount ").append(System.lineSeparator());
        for (int i = 0; i < 9; i++) {
            sb.append(i + 1).append(" count ").append(numsCount[i]).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());*/

        for (int i = 1; i <= 81; i++) {
            sb.append(this.numbers[i - 1]).append(" ");
            if (i % 9 == 0) {
                sb.append(System.lineSeparator());
            }
        }

        sb.append(System.lineSeparator());
        for (int i = 0; i < 81; i++) {
            int row = i / 9;
            int column = i % 9;
            sb.append(String.format("candidate for row %d column %d are", row, column));
            for (int j = 0; j < 9; j++) {
                if (this.candidates[i][j] > 0) {
                    sb.append(" ").append(j+1);
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
