package lesson_7;

import java.lang.reflect.InvocationTargetException;

public class HomeworkSeven {

    public static void main(String[] args) {

        // TestApp
        try {
            new TestApp().start(TestEx.class);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // HomeworkCheck
        try {
            new HomeworkCheckApp().check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}