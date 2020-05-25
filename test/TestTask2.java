import lesson_6.HomeworkSix;
import org.junit.Assert;
import org.junit.Test;

public class TestTask2 {

    @Test
    public void TestTrue1() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertTrue(hw6.task2(new int[]{1, 4, 1, 1, 4, 1}));
    }

    @Test
    public void TestTrue2() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertTrue(hw6.task2(new int[]{1, 1, 1}));
    }

    @Test
    public void TestTrue3() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertTrue(hw6.task2(new int[]{4}));
    }

    @Test
    public void TestFalse1() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertFalse(hw6.task2(new int[]{1, 1, 9, 1}));
    }

    @Test
    public void TestFalse2() {
        HomeworkSix hw6 = new HomeworkSix();
        Assert.assertFalse(hw6.task2(new int[]{1, 4, 4, 9}));
    }
}