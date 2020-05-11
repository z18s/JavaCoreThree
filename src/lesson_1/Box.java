package lesson_1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private T type;
    private ArrayList<T> content;

    public Box() {
        content = new ArrayList<>();
    }

    public Box(T... fruits) {
        this();
        add(fruits);
    }

    private void setType(T type) {
        this.type = type;
    }

    public float getWeight() {
        if (type == null) {
            return 0.0f;
        } else {
            return (type.getWeight() * content.size());
        }
    }

    public String getTypeToString() {
        if (type == null) {
            return "Unknown";
        } else {
            return type.toString();
        }
    }

    public void info() {
        System.out.printf("We have an %s box with %d items and its weight is %s.%n",
                getTypeToString(), content.size(), getWeight());
    }

    public void add(T fruit) {
        if (type == null) {
            setType(fruit);
        }
        content.add(fruit);
    }

    public void add(T... fruits) {
        for (T fruit : fruits) {
            add(fruit);
        }
    }

    public void clear() {
        content.clear();
    }

    public boolean compare(Box<?> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001;
    }

    public void transferTo(Box<? super T> newBox) {
        for (T fruit : content) {
            newBox.add(fruit);
        }
        clear();
    }
}