package Codes.Keywords;

class FinalClass{
    public final void method(){
        System.out.println("Parent method");
    }
}

class SubClass extends FinalClass{
    public void method(int a){
        System .out.println("Child");
    }
}

public class FinalKeyword {
    
}
