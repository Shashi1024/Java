public class Sample{
    int a ;
    static int x;
    public static void main(String [] args){
        int b;
        // public int y;
        final int g;
        // System.out.println(b);
        // System.out.println(g);
        Hello h = new Hello();
        System.out.println(h.d);
        System.out.println(x);
        System.out.println();
        System.out.println();
    }

    static void meth(){
        int c;
        // System.out.println(c);
    }
}

class Hello{
    int d;
    void met(){
        int e;
        // System.out.println(e);
    }
}