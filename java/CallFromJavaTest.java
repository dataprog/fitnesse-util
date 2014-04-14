import org.dataprog.fitnesse.fixture.cmd.CmdFixture;
import org.dataprog.fitnesse.fixture.cmd.BashFixture;
import org.dataprog.fitnesse.fixture.cmd.CmdTearDown;
import org.dataprog.fitnesse.fixture.clj.CljFixture;

public class CallFromJavaTest {

    private static Runnable[] TESTS = new Runnable[] {
        new CljFixtureTest()          ,
        new BashFixtureTest()         ,
        new BashFixtureAdvancedTest() ,
        new CmdFixtureTest()};

    public static void main(String[] args) {
        runAll();
    }

    private static final void runAll() {
        long start = System.currentTimeMillis();
        System.out.println();
        try {
            for (Runnable r : TESTS) {
                test(r);
            }
        } finally {
            // shutdown the agent pool ;-(
            new CmdTearDown().tearDown();
        }
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("    All tests OK (" + (end - start) + " ms)");
        System.out.println();
    }

    private static final class CljFixtureTest implements Runnable {
        public void run() {
            // when
            CljFixture f = new CljFixture();
            f.setIn("(do (print :foo) (+ 1 1))");
            // then
            assertEquals(f.result(), 2L);
            assertEquals(f.out()   , ":foo");
            assertEquals(f.err()   , "");
        }
    }

    private static final class BashFixtureTest implements Runnable {
        public void run() {
            // when
            BashFixture f = new BashFixture();
            f.setCmd("echo foo");
            // then
            assertEquals(f.exit() , 0);
            assertEquals(f.out()  , "foo\n");
            assertEquals(f.err()  , "");
        }
    }

    private static final class BashFixtureAdvancedTest implements Runnable {
        public void run() {
            // when
            BashFixture f = new BashFixture();
            f.setCmd("echo foo && echo bar"); // won't work with CmdFixture
            // then
            assertEquals(f.exit() , 0);
            assertEquals(f.out()  , "foo\nbar\n");
            assertEquals(f.err()  , "");
        }
    }

    private static final class CmdFixtureTest implements Runnable {
        public void run() {
            // when
            CmdFixture f = new CmdFixture();
            f.setCmd("echo bar");
            // then
            assertEquals(f.exit() , 0);
            assertEquals(f.out()  , "bar\n");
            assertEquals(f.err()  , "");
        }
    }

    private static void test(Runnable r) {
        System.out.print("Running " + r.getClass().getSimpleName() + " ... ");
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        System.out.println("OK (" + (end - start) + " ms)");
    }

    private static void assertEquals(Object actual, Object expected) {
        if (!actual.equals(expected)) {
            throw new RuntimeException("Expected " + expected + " but was " + actual);
        }
    }

}
