package lesson_8;

public class HelixArray {

    private final int[][] array;

    public HelixArray(int size) {
        array = new int[size][size];
        init(size);
    }

    private void init(int arrLength) {
        int arrCapacity = arrLength * arrLength;
        int sideLength = arrLength;

        int x = 0, y = 0;
        int dx = 1, dy = 0;

        int swap = 0;
        int index = 1;

        while (index <= arrCapacity) {

            array[y][x] = index;

            sideLength--;

            if (sideLength == 0) {
                sideLength = arrLength - swap / 2 - 1;

                int temp = dx;
                dx = -dy;
                dy = temp;

                swap++;
            }

            x += dx;
            y += dy;

            index++;
        }
    }

    public void print() {
        for (int[] row : array) {
            for (int i : row) {
                System.out.printf("%3d ", i);
            }
            System.out.println();
        }
    }
}