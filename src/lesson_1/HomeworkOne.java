package lesson_1;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeworkOne {

    public static void main(String[] args) {

        // Task 1

        Object[] objArr = {11111, new Apple(), "world", 9.99f, "hello"};
        printArr(objArr);

        taskOne_changeArrWithIndex(objArr, 1, 4);
        printArr(objArr);

        System.out.println("***");

        // Task 2

        ArrayList<Object> arrList = taskTwo_arrayToArrayList(objArr);
        System.out.println(arrList.getClass());
        System.out.println(arrList);

        System.out.println("***");

        // Task 3

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());

        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange(), new Orange());

        appleBox.info();
        orangeBox.info();

        boolean isEqually = appleBox.compare(orangeBox);
        System.out.printf("Boxes are%s equals.%n", (isEqually ? "" : "n't"));
        System.out.println();

        orangeBox.add(new Orange());

        appleBox.info();
        orangeBox.info();

        isEqually = appleBox.compare(orangeBox);
        System.out.printf("Boxes are%s equals.%n", (isEqually ? "" : "n't"));
        System.out.println();


        System.out.println("Refilled:");

        Box<Apple> appleBox2 = new Box<>();
        appleBox.transferTo(appleBox2);

        appleBox.info();
        appleBox2.info();

        System.out.println("-");

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox.transferTo(orangeBox2);

        orangeBox.info();
        orangeBox2.info();
    }

    public static void taskOne_changeArrWithIndex(Object[] arr, int from, int to) {
        if (from < arr.length && to < arr.length) {
            Object temp = arr[to];
            arr[to] = arr[from];
            arr[from] = temp;
        }
    }

    public static void printArr(Object[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static ArrayList<Object> taskTwo_arrayToArrayList(Object[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}