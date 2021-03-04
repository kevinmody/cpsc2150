package cpsc2150.MyDeque;
//Author: Kevin Mody and Henry Mayo
//Class: CPSC 2151
//Sec: 006
//Date: 03/04/2021
import org.junit.Test;


public class TestListDeque {
    private IDeque <Character> MakeADeque(){
        return new ListDeque <>();
    }

    @Test
    public void testEnqueueEmpty() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');

        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testEnqueueMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testEnqueueFull() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");
        for(int i = 0; i < 100; i++){
            q.enqueue('a');
            test.append("a, ");
        }
        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(">");

        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testDequeueEmpty() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');

        assert(q.dequeue().equals('a'));
        assert(q.toString().equals("<>"));
    }

    @Test
    public void testDequeueMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.dequeue().equals('a'));
        assert(q.toString().equals("<b>"));
    }

    @Test
    public void testDequeueFull() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");
        q.enqueue('a');
        for (int i = 0; i < 98; i++) {
            q.enqueue('a');
            test.append("a, ");
        }
        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(">");

        assert(q.dequeue().equals('a'));
        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testInjectEmpty() {
        IDeque<Character> q = MakeADeque();
        q.inject('a');

        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testInjectMiddle() {
        IDeque<Character> q = MakeADeque();
        q.inject('a');
        q.inject('b');

        assert(q.toString().equals("<b, a>"));
    }

    @Test
    public void testInjectFull() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");
        for(int i = 0; i < 100; i++){
            q.inject('a');
            test.append("a, ");
        }
        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(">");

        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testRemoveLastEmpty() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');

        assert(q.removeLast().equals('a'));
        assert(q.toString().equals("<>"));
    }

    @Test
    public void testRemoveLastMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.removeLast().equals('b'));
        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testRemoveLastFull() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");
        q.enqueue('a');
        for (int i = 0; i < 98; i++) {
            q.enqueue('a');
            test.append("a, ");
        }

        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(">");

        assert(q.removeLast().equals('a'));
        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testClearLastEmpty() {
        IDeque<Character> q = MakeADeque();
        q.clear();

        assert(q.toString().equals("<>"));
    }

    @Test
    public void testClearMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.clear();

        assert(q.toString().equals("<>"));
    }

    @Test
    public void testClearFull() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        for (int i = 0; i < 98; i++) {
            q.enqueue('a');
        }
        q.clear();


        assert(q.toString().equals("<>"));
    }

    @Test
    public void testPeekSizeOne() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');

        assert(q.peek().equals('a'));
        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testPeekSizeTwo() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.peek().equals('a'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testPeekMaxSize() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");
        for(int i = 0; i < 100; i++){
            q.enqueue('a');
            test.append("a, ");
        }
        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(">");

        assert(q.peek().equals('a'));
        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testEndOfDequeSizeOne() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');

        assert(q.endOfDeque().equals('a'));
        assert(q.toString().equals("<a>"));
    }

    @Test
    public void testEndOfDequeSizeTwo() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.endOfDeque().equals('b'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testEndOfDequeSize() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");
        for(int i = 0; i < 100; i++){
            q.enqueue('a');
            test.append("a, ");
        }
        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(">");

        assert(q.endOfDeque().equals('a'));
        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testInsertStart() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');

        q.insert('1', 1);

        assert(q.toString().equals("<1, a>"));
    }

    @Test
    public void testInsertMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        q.insert('1', 2);

        assert(q.toString().equals("<a, 1, b>"));
    }

    @Test
    public void testInsertEnd() {
        IDeque<Character> q = MakeADeque();
        StringBuilder test = new StringBuilder("<");

        for(int i = 0; i < 99; i++){
            q.enqueue('a');
            test.append("a, ");
        }
        test = new StringBuilder(test.substring(0, test.length() - 2));
        test.append(", 1>");

        q.insert('1', 100);

        assert(q.toString().equals(test.toString()));
    }

    @Test
    public void testRemoveStart() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.remove(1).equals('a'));
        assert(q.toString().equals("<b>"));
    }

    @Test
    public void testRemoveMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.remove(2).equals('b'));
        assert(q.toString().equals("<a, c>"));
    }

    @Test
    public void testRemoveEnd() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.remove(3).equals('c'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testGetStart() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');

        assert(q.get(1).equals('a'));
        assert(q.toString().equals("<a, b>"));
    }

    @Test
    public void testGetMiddle() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.get(2).equals('b'));
        assert(q.toString().equals("<a, b, c>"));
    }

    @Test
    public void testGetEnd() {
        IDeque<Character> q = MakeADeque();
        q.enqueue('a');
        q.enqueue('b');
        q.enqueue('c');

        assert(q.get(3).equals('c'));
        assert(q.toString().equals("<a, b, c>"));
    }
}


