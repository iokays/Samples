package analysispatterns.orgainzationstructures;


import org.junit.jupiter.api.Test;

public class Tester {

    interface  A {}

    interface  B {

    }

    public <T extends A & B> T get() {
        return null;
    }


    @Test
    public void test() {

    }

}
