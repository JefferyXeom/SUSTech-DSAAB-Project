import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class test1Test {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void main() {
        assertEquals(2, 1+1, "message");
    }
    @Test
    void TestMinus(){
        assertEquals("abc", "ab"+"c","111");
    }
    @Test
    void TestFail(){
        assertEquals("1", "1+");
    }
}