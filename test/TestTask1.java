import lesson_6.ArrayException;
import lesson_6.HomeworkSix;
import org.junit.Assert;
import org.junit.Test;

public class TestTask1 {

    @Test
    public void testOk1() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertArrayEquals(new int[] {1, 7}, hw6.task1(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test
    public void testOk2() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertArrayEquals(new int[] {}, hw6.task1(new int[] {3, 4, 1, 4}));
    }

    @Test
    public void testOk3() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertNotNull(hw6.task1(new int[] {4, 4, 4, 4}));
    }

    @Test (expected = ArrayException.class)
    public void testEx1() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertArrayEquals(new int[] {},hw6.task1(new int[] {3, 8, 7, 7, 2}));
    }

    @Test (expected = ArrayException.class)
    public void testEx2() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertArrayEquals(new int[] {},hw6.task1(new int[] {}));
    }
}