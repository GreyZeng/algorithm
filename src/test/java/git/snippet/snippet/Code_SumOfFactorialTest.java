package git.snippet.snippet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("给定一个参数N，返回1!+2!+3!+4!+…+N!的结果")
public class Code_SumOfFactorialTest {

    @Test
    void fTest() {
        int times = 18;
        Code_SumOfFactorial test = new Code_SumOfFactorial();
        Assertions.assertEquals(test.f1(11), 43954713L);
        Assertions.assertEquals(test.f2(11), 43954713L);
        for (int i = 1; i <= times; i++) {
            Assertions.assertEquals(test.f1(i), test.f2(i));
        }
    }
}