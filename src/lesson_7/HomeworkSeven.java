package lesson_7;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 1. Добавить на серверную сторону чата логирование, с выводом информации о действиях на сервере
 * (запущен, произошла ошибка, клиент подключился, клиент прислал сообщение/команду).
 *
 *
 * 1. Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов
 * с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве параметра
 * передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод
 * с аннотацией @BeforeSuite, если такой имеется. Далее запущены методы с аннотациями @Test, а по завершении
 * всех тестов – метод с аннотацией @AfterSuite. К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения. Если приоритет одинаковый, то порядок
 * не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном
 * экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
 *
 * Это домашнее задание никак не связано с темой тестирования через JUnit и использованием этой библиотеки:
 * проект пишется с нуля.
 *
 * 2. Создать консольное приложение для проверки ДЗ, исходный файл в приложении к уроку, тестировать только
 * методы которые возвращают результат.
 */

public class HomeworkSeven {

    public static void main(String[] args) {

        try {
            checkHW();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void checkHW() throws Exception {
        int year = 2020;

        File file = new File("resources");
        String[] str = file.list();

        for (String s : str) {
            String[] mass = s.split("\\.");
            if (!mass[1].equalsIgnoreCase("class")) {
                throw new RuntimeException(s, new Exception());
            }

            Class c = URLClassLoader.newInstance(new URL[]{file.toURL()}).loadClass(mass[0]);
            Constructor constructor = c.getConstructor(int.class);
            Object homework = constructor.newInstance(year);
            Method m = c.getDeclaredMethod("isNegative");
            m.invoke(homework);
        }
    }
}