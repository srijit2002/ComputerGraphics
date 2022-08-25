package nested.PACK1;

import nested.PACK1.PACK2.PACK3.Third;
import nested.PACK1.PACK2.Second;

public class First {
    public static void main(String args[]) {
        System.out.println("Hello this is the first class");

        Second p = new Second();
        p.print();

        Third q = new Third();
        q.print();;
    }
}