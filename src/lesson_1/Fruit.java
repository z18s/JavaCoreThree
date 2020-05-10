package lesson_1;

public abstract class Fruit {
    protected float weight;

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}