import java.lang.reflect.Method;

class Greetings{
  public void greet(String name){
    System.out.println("Hello "+name+"!");
  }
}

public class DynamicMethodInvocation{
  public static void main(String[] args) throws Exception{
    Class<Greetings> greetClass = Greetings.class;
    Greetings greetInstance = greetClass.getDeclaredConstructor().newInstance();
    
    Method greetMethod = greetClass.getMethod("greet", String.class);
    System.out.println("Method Object Obtained: "+greetMethod.getName());

    System.out.println("Invoking the Method Dynamically");
    greetMethod.invoke(greetInstance, "World");
  }
}
