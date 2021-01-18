package bruteforce;

public class Board {
    private Block[][] blocks = new Block[3][3];
    private int[][] nums = new int[9][9];
    private int[] numsCount = new int[9];
    private int totalNumsCount;
    private int[] numbers=new int[81];

    public Board(int[] input) {
        System.arraycopy(input, 0, this.numbers, 0, 81);
        int blockRow = 0;
        int row = 0;
        int insideBlockRow = 0;
        int[] block1 = new int[9];
        int[] block2 = new int[9];
        int[] block3 = new int[9];

        for (int i = 0; i < 9; i++) {

            System.arraycopy(input, i * 9, nums[i], 0, 9);
            for (int j = 0; j < 9; j++) {
                if (nums[i][j] == 0) {
                    continue;
                }
                numsCount[nums[i][j] - 1]++;
                totalNumsCount++;
            }

            System.arraycopy(input, i * 9, block1, insideBlockRow * 3, 3);
            System.arraycopy(input, i * 9 + 3, block2, insideBlockRow * 3, 3);
            System.arraycopy(input, i * 9 + 6, block3, insideBlockRow * 3, 3);
            row++;
            insideBlockRow++;
            if (row % 3 == 0) {
                this.blocks[blockRow][0] = new Block(blockRow, 0, block1);
                this.blocks[blockRow][1] = new Block(blockRow, 1, block2);
                this.blocks[blockRow][2] = new Block(blockRow, 2, block3);
                insideBlockRow = 0;
                blockRow++;
                block1 = new int[9];
                block2 = new int[9];
                block3 = new int[9];
            }
        }
    }

    public void solve() {
        trySolve(0);
    }

    private boolean trySolve(int start) {
        for (int i = start; i < 81; i++) {
            if(this.numbers[i]>0){
                continue;
            }
            int row=i/9;
            int column=i%9;
            int foundCount=0;
            for (int k = 1; k <=9 ; k++) {
                if (tryPlaceNumber(row,column, k)) {
                    foundCount++;
                    totalNumsCount++;
                    this.nums[row][column] = k;
                    this.numbers[i]=k;
                    System.out.println(String.format("placed %d at row %d column %d", k, row, column));
                    System.out.println(this.toString());
                    if (totalNumsCount == 81) {
                        return true;
                    } else {
                        int nxt=i+1;
                        if (trySolve(nxt)) {
                            return true;
                        } else {
                            System.out.println(String.format("failed search num %d at row %d column %d", k, row, column));
                            totalNumsCount--;
                            this.nums[row][column] = 0;
                            this.numbers[i]=0;
                            foundCount--;
                        }
                    }
                }
            }
            if(foundCount==0){
                System.out.println(String.format("failed search at row %d column %d", row, column));
                return false;
            }


        }


        return false;
    }

    private boolean tryPlaceNumber(int row, int column, int num) {
        System.out.println(String.format("try place %d at row %d column %d", num, row,column));
        if (!canPlaceRowAndColumn(row, column, num)) {
            System.out.println("row or column violation");
            return false;
        }
        if (!canPlaceBlock(row, column, num)) {
            System.out.println("block violation");
            return false;
        }


        return true;

    }

    private boolean canPlaceRowAndColumn(int row, int column, int num) {
        for (int i = 0; i < 9; i++) {
            if (this.nums[row][i] == num || this.nums[i][column] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean canPlaceBlock(int row, int column, int num){
        int blockRow=row/3;
        int blockColumn=column/3;
        for (int i = blockRow*3; i < blockRow*3+3; i++) {
            for (int j = blockColumn*3; j < blockColumn*3+3; j++) {
                if(this.nums[i][j]==num){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
       /* sb.append("numsCount ").append(System.lineSeparator());
        for (int i = 0; i < 9; i++) {
            sb.append(i + 1).append(" count ").append(numsCount[i]).append(System.lineSeparator());
        }
        sb.append(System.lineSeparator());*/

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(nums[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }

       /* for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append("row ").append(i).append(" column ").append(j).append(System.lineSeparator());
                sb.append(blocks[i][j].toString()).append(System.lineSeparator());
            }
        }*/
        return sb.toString();
    }

}
