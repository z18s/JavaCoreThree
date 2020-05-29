package lesson_7;

public class Petrov {

    private static int calculate(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    private static float calculate(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    private static boolean checkTwoNumbers(int first, int second) {
        int sum = first + second;
        return sum <= 20 && sum >= 10;
    }

    private static void printIsPositive(int variable) {
        System.out.println("Your number is " + ((variable >= 0) ? "positive" : "negative") + "!");
    }

    private static boolean isNegative(int variable) {
        return (variable < 0);
    }

    private static void printWelcome(String name) {
        System.out.println("Привет, " + name + "!");
    }

    private static boolean isLeapYear(int year) {
        return (year % 100 != 0) && (year % 4 == 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        byte b = 127;
        short s = 32767;
        int i = 1234;
        long l = 4321L;
        char c = 65;
        float f = 1234.5678f;
        double d = 5678.1234;
        boolean boo = true;
        String str = "Fancy String";

        final int varOne = 1;
        final int varTwo = 1;
        final int varThree = 1;
        final int varFour = 1;

        final float fOne = 1;
        final float fTwo = 2;
        final float fThree = 3;
        final float fFour = 4.0f;

        System.out.println("Calculation from the first task gives us: " + calculate(varOne, varTwo, varThree, varFour));

        System.out.println("Overloaded method returns: " + calculate(fOne, fTwo, fThree, fFour));

        System.out.println("Does the sum of two given numbers fit the range? " + checkTwoNumbers(1, 15));

        printIsPositive(-1);

        System.out.println("The variable in sixth task returns: " + isNegative(-1));

        printWelcome("Somename");

        System.out.println("Is the given year leap? " + isLeapYear(0));
    }
}