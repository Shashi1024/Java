package Codes.Keywords;

import static java.lang.System.*; // importing static members of System, now i can use "out" without System

public class StaticKeyword {

    static int val = 0; // static variable
    static int newVal = 10;

    static void add(int a, int b){
        // static method
        System.out.println("Static Method: "+a+b);
    }

    static {
        // static initializer block
        out.println("Static Initializer");
        val = 20;
        int j = 0;
        // j is a local variable and gets destroyed as soon as the block finishes execution
    }


    static class nestedClass{
        static int nestedVal = 10;

        void method(){
            out.println("inner class non-static maethod: "+nestedVal);
            // can access outer class static members but not non-static members
        }
        static void staticMethod(){
            out.println("inner class static method");
        }
    }


    public static void main(String[] args){
        out.println("Main Method Started");
        out.println(StaticKeyword.newVal);

        StaticKeyword.add(2, 3);

        StaticKeyword stk = new StaticKeyword();
        out.println("access using Object Reference");
        stk.add(1, 5); // invokation via object reference (discouraged)
        out.println(stk.newVal);
        out.println();

        StaticKeyword.nestedClass stnc = new StaticKeyword.nestedClass();
        stnc.method();
        stnc.staticMethod();
        StaticKeyword.nestedClass.staticMethod(); // invokation using class names

        out.println("able to use out.println without System due to static imports");
        out.println("Main method complete");
    }
}

