package lesson_6;

import java.util.Arrays;

public class HomeworkSix {

    public static void main(String[] args) {}

    public int[] task1(int[] arr) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                index = i;
            }
        }
        if (index == -1) {
            throw new ArrayException();
        }
        return Arrays.copyOfRange(arr, index + 1, arr.length);
    }

    public boolean task2(int[] arr) {
        for (int a : arr) {
            if (a != 1 && a != 4) {
                return false;
            }
        }
        return true;
    }
}