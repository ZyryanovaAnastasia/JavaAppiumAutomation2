package homework;

import org.junit.Assert;
import org.junit.Test;

public class HWMainClassTest {

    HWMainClass Main = new HWMainClass();

    @Test
    public void testGetLocalNumber()
    {
        Assert.assertTrue("Метод getLocalNumder не возвращает 14",Main.getLocalNumber() == 14 );
    }

    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("Метод getClassNumber возвращает число меньше 45",Main.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString()
    {
        boolean result = Main.getClassString().toLowerCase().contains("hello");
        if (!result)
        {
            Assert.fail("В значении метода getClassString нет фразы 'hello' или 'Hello'");
        }
    }
}
