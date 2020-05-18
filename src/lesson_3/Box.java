package lesson_3;

import java.io.Serializable;

public class Box implements Serializable {
    String content;
    int amount;

    public Box(String content, int amount) {
        this.content = content;
        this.amount = amount;
    }

    public void info () {
        System.out.printf("The box contains %d %s(s).%n", amount, content);
    }
}