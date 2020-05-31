package lesson_7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestApp {

    private ArrayList<Method> methodsForTest = new ArrayList<>();
    private Method methodBeforeTest = null;
    private Method methodAfterTest = null;

    public void start(Class c) throws InvocationTargetException, IllegalAccessException {

        Method[] methods = c.getDeclaredMethods();

        setTests(methods);

        sort();

        setBeforeAndAfterTests();

        run(c);
    }

    private void setTests(Method... methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                methodsForTest.add(method);
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (methodBeforeTest == null) {
                    methodBeforeTest = method;
                } else {
                    throw new RuntimeException("Annotation @BeforeSuite already exists.");
                }
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (methodAfterTest == null) {
                    methodAfterTest = method;
                } else {
                    throw new RuntimeException("Annotation @AfterSuite already exists.");
                }
            }
        }
    }

    private void sort() {
        methodsForTest.sort(new Comparator<Method>() {
            @Override
            public int compare(Method m1, Method m2) {
                return m2.getAnnotation(Test.class).priority() - m1.getAnnotation(Test.class).priority();
            }
        });
    }

    private void setBeforeAndAfterTests() {
        if (methodBeforeTest != null) {
            methodsForTest.add(0, methodBeforeTest);
        }
        if (methodAfterTest != null) {
            methodsForTest.add(methodsForTest.size(), methodAfterTest);
        }
    }

    private void run(Class c) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methodsForTest) {
            method.invoke(c);
        }
    }
}