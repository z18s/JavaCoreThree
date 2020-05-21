package lesson_5;

import java.util.concurrent.BrokenBarrierException;

public class HomeworkFive {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(CARS_COUNT, new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40, true));

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        race.getBarrier().await();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        race.getBarrier().await();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}