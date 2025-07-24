package Codes.classes_objects;

public class OuterClass {
    private String outerMessage = "Hello from OuterClass!";
    private static String staticOuterMessage = "Static message from OuterClass!";

    // 1. Member Inner Class (Non-static Inner Class)
    // Belongs to an instance of the outer class.
    // Can access all members (even private) of the outer class instance.
    class MemberInnerClass {
        String innerMessage = "Hello from MemberInnerClass!";

        public void displayMessages() {
            System.out.println("  MemberInnerClass: " + innerMessage);
            System.out.println("  Accessing outerMessage: " + outerMessage); // Accesses outer class instance variable
            System.out.println("  Accessing staticOuterMessage: " + staticOuterMessage); // Accesses outer class static variable
        }

        // Cannot declare static members unless they are final constants
        // public static int count = 0; // Compile-time error
        public static final int CONSTANT = 10; // Allowed
    }

    // 2. Static Nested Class
    // Belongs to the outer class itself, not an instance.
    // Can only access static members of the outer class directly.
    static class StaticNestedClass {
        String nestedMessage = "Hello from StaticNestedClass!";
        static String staticNestedMessage = "Static message from StaticNestedClass!";

        public void displayMessages() {
            System.out.println("  StaticNestedClass: " + nestedMessage);
            System.out.println("  Accessing staticOuterMessage: " + staticOuterMessage); // Accesses outer class static variable
            // System.out.println(outerMessage); // Compile-time error: cannot access non-static outer member
        }

        public static void displayStaticNestedMessage() {
            System.out.println("  StaticNestedClass Static Method: " + staticNestedMessage);
        }
    }

    public void outerMethod() {
        String methodLocalVariable = "Local variable in outerMethod"; // Effectively final

        // 3. Local Class
        // Defined inside a method or block.
        // Scope is limited to the block it's defined in.
        // Can access final or effectively final local variables of the enclosing block.
        class LocalClass {
            String localMessage = "Hello from LocalClass!";

            public void displayMessages() {
                System.out.println("  LocalClass: " + localMessage);
                System.out.println("  Accessing outerMessage: " + outerMessage); // Accesses outer class instance variable
                System.out.println("  Accessing methodLocalVariable: " + methodLocalVariable); // Accesses effectively final local variable
                // methodLocalVariable = "changed"; // If uncommented, methodLocalVariable would no longer be effectively final
            }
        }

        LocalClass localObj = new LocalClass();
        localObj.displayMessages();
    }
    public static void main(String[] args) {
        System.out.println("--- Inner Classes Demonstration ---");

        // Instantiating Member Inner Class
        System.out.println("\n1. Member Inner Class:");
        OuterClass outerObj = new OuterClass();
        OuterClass.MemberInnerClass memberInnerObj = outerObj.new MemberInnerClass();
        memberInnerObj.displayMessages();
        System.out.println("  MemberInnerClass constant: " + OuterClass.MemberInnerClass.CONSTANT);


        // Instantiating Static Nested Class
        System.out.println("\n2. Static Nested Class:");
        OuterClass.StaticNestedClass staticNestedObj = new OuterClass.StaticNestedClass();
        staticNestedObj.displayMessages();
        OuterClass.StaticNestedClass.displayStaticNestedMessage();


        // Instantiating Local Class (by calling the outer method)
        System.out.println("\n3. Local Class:");
        outerObj.outerMethod();


        // 4. Anonymous Class
        // An unnamed class that implements an interface or extends a class.
        // Defined and instantiated in a single expression.
        System.out.println("\n4. Anonymous Class:");

        // Implementing an interface using an anonymous class
        Runnable runnable = new Runnable() {
            private String anonymousMessage = "Hello from Anonymous Runnable!";
            @Override
            public void run() {
                System.out.println("  Anonymous Runnable: " + anonymousMessage);
                System.out.println("  Accessing outerMessage: " + outerObj.outerMessage); // Accesses outer class instance variable
                // Can access effectively final local variables from enclosing scope
                String localToMain = "Local variable in main"; // Effectively final
                System.out.println("  Accessing local variable from main: " + localToMain);
            }
        };
        runnable.run();

        // Extending a class using an anonymous class
        Thread anonymousThread = new Thread("MyAnonymousThread") {
            @Override
            public void run() {
                System.out.println("  Anonymous Thread: " + Thread.currentThread().getName() + " is running.");
            }
        };
        anonymousThread.start();
    }
}
