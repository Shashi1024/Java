package Codes.Enum;


enum Operation {
    ADD {
        public int apply(int x, int y) { return x + y; }
    },
    SUBTRACT {
        public int apply(int x, int y) { return x - y; }
    },
    MULTIPLY {
        public int apply(int x, int y) { return x * y; }
    };


    public void instanceMethod(){
        System.out.println("Instance method of "+name());
    }

    // Static method (can be added to enums)
    public static void printAllOps() {
        System.out.println("\n--- All Ops ---");
        for (Operation op : Operation.values()) {
            System.out.println(op); // Uses the overridden toString()
        }
    }

    // Abstract method to be implemented by each enum constant
    public abstract int apply(int x, int y);
}

public class EnumMethods {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;

        System.out.println("Addition: " + Operation.ADD.apply(a, b));
        System.out.println("Subtraction: " + Operation.SUBTRACT.apply(a, b));
        System.out.println("Multiplication: " + Operation.MULTIPLY.apply(a, b));

        Operation.ADD.instanceMethod();

        Operation.printAllOps();
    }
}
