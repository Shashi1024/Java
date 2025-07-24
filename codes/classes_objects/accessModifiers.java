package codes.classes_objects;

// Demonstrates Encapsulation using private fields and public getters/setters,
// and the usage of various access modifiers.

// Class demonstrating Encapsulation
class BankAccount {
    // Private fields: Data hiding. These can only be accessed within this class.
    private String accountNumber;
    private double balance;
    private String accountHolderName;

    // Constructor to initialize the account
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        // Validate initial balance to ensure data integrity
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance cannot be negative. Setting to 0.");
        }
    }

    // Public Getter methods: Provide controlled read access to private fields.
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    // Public Setter methods: Provide controlled write access to private fields.
    // Logic can be added to validate input before modifying the state.
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited: $" + amount + ". New balance: $" + this.balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            System.out.println("Withdrew: $" + amount + ". New balance: $" + this.balance);
        } else if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else {
            System.out.println("Insufficient funds. Current balance: $" + this.balance);
        }
    }

    // Default (package-private) method: Accessible only within the same package.
    void printAccountSummary() {
        System.out.println("Account Summary (Default Access):");
        System.out.println("  Account Number: " + accountNumber);
        System.out.println("  Holder: " + accountHolderName);
        System.out.println("  Balance: $" + balance);
    }

    // Protected method: Accessible within the same package and by subclasses (even in different packages).
    protected String getProtectedInfo() {
        return "Protected Info: Account " + accountNumber;
    }
}

// Another class in the same package to demonstrate default access
class PackageAccessDemo {
    void testAccess(BankAccount account) {
        System.out.println("\n--- Testing Package-Private Access ---");
        account.printAccountSummary(); // Accessible because PackageAccessDemo is in the same package
        // System.out.println(account.accountNumber); // Compile-time error: accountNumber is private
    }
}

// A subclass (could be in a different package, but for simplicity, it's here)
class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }

    public void showProtectedInfoFromSubclass() {
        System.out.println("\n--- Testing Protected Access from Subclass ---");
        System.out.println(getProtectedInfo()); // Accessible because it's a subclass
    }
}

public class accessModifiers {

    public static void main(String[] args) {
        System.out.println("--- Encapsulation and Access Modifiers Demonstration ---");

        // Creating a BankAccount object
        BankAccount myAccount = new BankAccount("123456789", "John Doe", 1000.00);

        // Accessing data using public getters (controlled access)
        System.out.println("\n--- Encapsulation (Controlled Access) ---");
        System.out.println("Account Holder: " + myAccount.getAccountHolderName());
        System.out.println("Current Balance: $" + myAccount.getBalance());

        // Modifying data using public setters (controlled mutation)
        myAccount.deposit(500.00);
        myAccount.withdraw(200.00);
        myAccount.withdraw(1500.00); // Attempt to withdraw more than balance

        // Attempting direct access to private fields (compile-time error)
        // myAccount.balance = 5000.00; // Compile-time error: balance has private access

        // Demonstrating Default (package-private) Access
        PackageAccessDemo packageTester = new PackageAccessDemo();
        packageTester.testAccess(myAccount);

        // Demonstrating Protected Access
        SavingsAccount savings = new SavingsAccount("987654321", "Jane Smith", 5000.00);
        savings.showProtectedInfoFromSubclass();

        // Demonstrating Public Access (main method itself is public)
        System.out.println("\n--- Public Access ---");
        System.out.println("Public methods like 'main' and 'deposit' are accessible from anywhere.");
    }
}
