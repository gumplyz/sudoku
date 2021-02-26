package smart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartSudokuTest {
    @Test
    void solve1() {
        int[] input = new int[]{0, 2, 5, 3, 4, 1, 7, 9, 8,
                4, 9, 8, 6, 7, 2, 3, 1, 5,
                3, 7, 1, 5, 9, 8, 4, 2, 6,
                8, 5, 2, 1, 6, 7, 9, 3, 4,
                1, 4, 9, 8, 3, 5, 6, 7, 2,
                7, 3, 6, 4, 2, 9, 5, 8, 1,
                5, 8, 7, 9, 1, 6, 2, 4, 3,
                2, 6, 3, 7, 8, 4, 1, 5, 9,
                9, 1, 4, 2, 5, 3, 8, 6, 0};
        Board b = new Board(input);
        System.out.println(b.toString());
        SmartSudoku s = new SmartSudoku();
        s.solve(b);
    }

    @Test
    void testSolve2() {
        int[] input = new int[]{6, 2, 0, 0, 0, 1, 7, 0, 8,
                0, 0, 0, 0, 7, 0, 3, 0, 5,
                0, 7, 1, 5, 0, 0, 4, 0, 0,
                8, 0, 0, 1, 0, 7, 9, 0, 0,
                0, 4, 0, 0, 3, 0, 0, 7, 0,
                0, 0, 6, 4, 0, 9, 0, 0, 1,
                0, 0, 7, 0, 0, 6, 2, 4, 0,
                2, 0, 3, 0, 8, 0, 0, 0, 0,
                9, 0, 4, 2, 0, 0, 0, 6, 7};
        Board b = new Board(input);
        System.out.println(b.toString());
        SmartSudoku s = new SmartSudoku();
        s.solve(b);
    }

    @Test
    void testSolve3() {
        int[] input = new int[]{
                0, 2, 1, 0, 0, 0, 0, 0, 0,
                9, 0, 0, 5, 2, 0, 0, 0, 0,
                0, 0, 8, 0, 3, 4, 0, 0, 9,
                2, 7, 0, 0, 0, 0, 0, 8, 0,
                0, 0, 0, 0, 5, 0, 0, 0, 0,
                0, 3, 0, 0, 0, 0, 0, 7, 6,
                1, 0, 0, 6, 7, 0, 2, 0, 0,
                0, 0, 0, 0, 4, 9, 0, 0, 8,
                0, 0, 0, 0, 0, 0, 4, 1, 0
        };
        Board b = new Board(input);
        System.out.println(b.toString());
        SmartSudoku s = new SmartSudoku();
        s.solve(b);
    }

    @Test
    void testSolve4() {
        int[] input = new int[]{
                7, 6, 0, 0, 0, 0, 0, 0, 2,
                0, 0, 0, 0, 0, 0, 0, 9, 0,
                3, 0, 0, 9, 5, 4, 0, 0, 0,
                0, 0, 0, 0, 0, 9, 4, 6, 0,
                0, 0, 0, 7, 8, 0, 0, 2, 9,
                5, 0, 0, 0, 0, 6, 7, 1, 0,
                1, 8, 0, 0, 0, 0, 2, 0, 0,
                0, 0, 0, 0, 1, 0, 0, 0, 6,
                0, 0, 0, 0, 0, 0, 8, 0, 1
        };
        Board b = new Board(input);

        SmartSudoku s = new SmartSudoku();
        s.solve(b);
    }
}