package lesson_3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
 * ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
 * 3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 * Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль. Контролируем время
 * выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 * 4. Сделать клиен-серверное приложение. Передать по сети сеарилизованный объект.
 */

public class HomeworkThree {

    public static <FileOutputStreamStream> void main(String[] args) {

        // Task 1

        try (FileInputStream in = new FileInputStream("src/lesson_3/hw3_t1.txt")) {
            byte[] arr = new byte[64];
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.println(Arrays.toString(arr));
                System.out.println(new String(arr, 0, x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        // Task 2

        ArrayList<InputStream> alin = new ArrayList<>();
        try {
            alin.add(new FileInputStream("src/lesson_3/hw3_t2_1.txt"));
            alin.add(new FileInputStream("src/lesson_3/hw3_t2_2.txt"));
            alin.add(new FileInputStream("src/lesson_3/hw3_t2_3.txt"));
            alin.add(new FileInputStream("src/lesson_3/hw3_t2_4.txt"));
            alin.add(new FileInputStream("src/lesson_3/hw3_t2_5.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SequenceInputStream seqin = new SequenceInputStream(Collections.enumeration(alin));

        File file = new File("src/lesson_3/hw3_t2_result.txt");
        int x;
        try {
            FileOutputStream out = new FileOutputStream(file);
            while ((x = seqin.read()) > 0) {
                out.write(x);
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        // Task 3

        Scanner scan = new Scanner(System.in);

        try (RandomAccessFile raf = new RandomAccessFile("src/lesson_3/hw3_t3.txt", "r")) {
            int n;
            while (true) {
                System.out.println("\nChoose a page number. Type '0' for exit.");
                n = scan.nextInt();
                System.out.println();

                if (n > 0) {
                    long t = System.currentTimeMillis();

                    for (int i = 1800 * (n - 1); i < 1800 * n; i++) {
                        raf.seek(i);
                        System.out.print(((char)raf.read()));
                    }

                    System.out.println("\n\nLoading time: " + (System.currentTimeMillis() - t));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scan.close();
    }
}