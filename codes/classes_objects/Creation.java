package codes.classes_objects;

public class Creation{
    int a ;
    static int x;
    public static void main(String [] args){
        Hello h = new Hello();
        // Hello h1 = new Hello();
    }

    static void meth(){
        int c;
        // System.out.println(c);
    }
}

class Big{
    Big(int a){
        System.out.println("base constructoer");
    }
    {
        System.out.println("base instance block");
    }
    static {
        System.out.println("base static block");

    }

}

class Hello extends Big{
    int d = 0;
    
    Hello(){
        this(2);
        // super(2);
        // this(2);
        System.out.println("child Constructor");
        meth();
    }
    Hello(int b){
        super(2);
        System.out.println("Child constructor 2");
    }

    void meth(){
        System.out.println("child method");
    }

    {
        System.out.println("child instance block");
        System.out.println(d);
    }
    int c = 1;
    static {
        System.out.println("child static block");
    }

}
