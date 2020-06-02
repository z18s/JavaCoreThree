package lesson_7;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class HomeworkCheckApp {

    public void check() throws Exception {

        File dir = new File("resources");
        String[] str = dir.list();

        ArrayList<String> files = new ArrayList<>();

        for (String s : str) {
            String[] mass = s.split("\\.");
            if (mass[1].equalsIgnoreCase("class")) {
                files.add(mass[0]);
            } else {
                throw new RuntimeException(s, new Exception());
            }
        }

        for (String file : files) {

            Class c = URLClassLoader.newInstance(new URL[]{dir.toURL()}).loadClass(file);
            Constructor constructor = c.getConstructor();
            Object obj = constructor.newInstance();

            System.out.println();
            System.out.println(file + ":");

            Method m1 = c.getDeclaredMethod("calculate", int.class, int.class, int.class, int.class);
            Object val1 = m1.invoke(obj, 4, 3, 8, 2);
            int x1 = (int) val1;

            checkResult(x1 == 28, "calculate");

            Method m2 = c.getDeclaredMethod("checkTwoNumbers", int.class, int.class);
            Object val2 = m2.invoke(obj, 1, 15);
            boolean x2 = (boolean) val2;

            checkResult(x2, "checkTwoNumbers");

            Method m3 = c.getDeclaredMethod("isNegative", int.class);
            Object val3 = m3.invoke(obj, -3);
            boolean x3 = (boolean) val3;

            checkResult(x3, "isNegative");

            Method m4 = c.getDeclaredMethod("isLeapYear", int.class);
            Object val4 = m4.invoke(obj, 2020);
            boolean x4 = (boolean) val4;

            checkResult(x4, "isLeapYear");
        }
    }

    private void checkResult(boolean condition, String name) {
        if (condition) {
            System.out.printf("+ %s passed.%n", name);
        } else {
            System.out.printf("- %s failed.%n", name);
        }
    }
}