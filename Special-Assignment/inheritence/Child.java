package inheritence;
import inheritence.base.Parent;

public class Child extends Parent{
    public void print(){
        super.print();
        System.out.println("Hi am Child class");
    }
    public static void main(String[] args) {
        new Child().print();
    }
}
