package lesson_7;

public class TestEx {

    @BeforeSuite
    public static void start() {
        System.out.println("Start.");
    }

    @AfterSuite
    public static void finish() {
        System.out.println("Finish.");
    }

    @Test(priority = 8)
    public static void test1() {
        System.out.println("Test - Priority 8.");
    }

    @Test(priority = 4)
    public static void test2() {
        System.out.println("Test - Priority 4.");
    }

    @Test(priority = 2)
    public static void test3() {
        System.out.println("Test - Priority 2.");
    }

    @Test
    public static void test4() {
        System.out.println("Test - Priority 5 (default).");
    }

    @Test(priority = 6)
    public static void test5() {
        System.out.println("Test - Priority 6.");
    }
}