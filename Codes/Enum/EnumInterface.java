package Codes.Enum;

interface Command {
    void execute();
}

enum Action implements Command {
    SAVE {
        @Override
        public void execute() {
            System.out.println("Saving document...");
        }
    },
    OPEN {
        @Override
        public void execute() {
            System.out.println("Opening file...");
        }
    },
    CLOSE {
        @Override
        public void execute() {
            System.out.println("Closing application...");
        }
    };
}

public class EnumInterface {
    public static void main(String[] args) {
        Action.SAVE.execute();
        Action.OPEN.execute();
        Action.CLOSE.execute();
    }
}