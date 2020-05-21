package lesson_5;

public class Road extends Stage {
    private boolean isLastStage = false;
    private volatile boolean haveWinner = false;

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    public Road(int length, boolean isLastStage) {
        this(length);
        this.isLastStage = isLastStage;
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);

            Thread.sleep(length / c.getSpeed() * 1000);

            if (isLastStage && !haveWinner) {
                haveWinner = true;
                c.setWinner(true);
            }

            System.out.println(c.getName() + " закончил этап: " + description + (c.isWinner() ? " (WINNER)" : ""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}