package codes.keywords;

public class ThisKeyword {
    String name;
    int val;

    ThisKeyword(){
        this("noName", 0);
        System.out.println("Constructor Chaining, values are initialized name: "+this.name+" val: "+this.val);
        // Constructor Chaining
    }

    ThisKeyword(String name, int val){
        this.name = name;
        this.val = val;
        // here "this" refers to the instance variables (Disambiguation)
    }

    void invokeMethod(){
        this.method();
        // invoking method on current instance
    }
    void method(){
        System.out.println("Method invoked on current instance Val = "+this.val);
    }

    void sendRef(){
        refMethod(this);
        // sending current object reference as argument
    }
    void refMethod(ThisKeyword tk){
        System.out.println("Recieved current instance as parameter name: "+tk.name+" Val: "+tk.val);
    }

    ThisKeyword setName(String n){
        this.name = n;
        return this;
        // returning current object
    }
    ThisKeyword setVal(int v){
        this.val = v;
        return this;
    }

    String outerColour = "Red";

    class InnerClass{
        String innerColour = "Blue";

        void printColour(){
            System.out.println("Inner Colour: "+this.innerColour);
            System.out.println("Outer Colour: "+ThisKeyword.this.outerColour);
        }
    }

    public static void main(String[] args){
        ThisKeyword tk1 = new ThisKeyword("hello", 1);
        tk1.invokeMethod();
        
        ThisKeyword tk2 = new ThisKeyword();

        tk1.sendRef();

        tk1.setName("newName").setVal(10);
        System.out.println("Fields of tk1 are modified using the returned reference --> name: "+tk1.name+" Val: "+tk1.val);

        ThisKeyword.InnerClass tkin = tk1.new InnerClass();
        tkin.printColour();
    }
}