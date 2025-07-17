public class Sample{
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
        System.out.println("big cons");
    }
    {
        System.out.println("Big ins");
    }
    static {
        System.out.println("Big static");

    }

}

class Hello extends Big{
    int d = 0;
    
    Hello(){
        this(2);
        // super(2);
        // this(2);
        System.out.println("Constructor");
        meth();
    }
    Hello(int b){
        super(2);
        System.out.println("Child b");
    }

    void meth(){
        System.out.println("meth");
    }

    {
        System.out.println("ins block");
        System.out.println(d);
    }
    int c = 1;
    static {
        System.out.println("static block");
    }

}