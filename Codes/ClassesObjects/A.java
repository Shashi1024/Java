package Codes.ClassesObjects;

import java.io.Serializable;

public class A implements Cloneable, Serializable{
    String name;
    int val;

    A(){
        name = "Nothing";
        val = 0;
    }

    A(int x){
        this.val = x;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}