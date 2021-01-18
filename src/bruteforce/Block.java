package bruteforce;

public class Block {
    private int row;
    private int column;
    private int[][] nums=new int[3][3];
    private int[] numsCount=new int[9];

    public Block(int row, int column, int[] input) {
        this.row = row;
        this.column = column;
        for (int i = 0; i < 3; i++) {
            this.nums[i] = new int[3];
            for (int j = 0; j < 3; j++) {
                this.nums[i][j] = input[3 * i + j];
                if (nums[i][j] > 0) {
                    numsCount[nums[i][j] - 1]++;;
                }

            }
        }
    }

    public boolean tryPlaceNumber(int row, int column, int num){
        if(this.nums[row][column]>0||this.numsCount[num-1]>0){
            return false;
        }
        this.nums[row][column]=num;
        this.numsCount[num-1]++;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("row ").append(this.row).append(" column ").append(this.column).append(System.lineSeparator());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(nums[i][j]).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
