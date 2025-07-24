enum CoffeeSize{
    SMALL(8, "S"),
    MEDIUM(12, "M"),
    LARGE(16, "L");

    private final int quant;
    private final String code;

    CoffeeSize(int q, String c) {
        this.quant = q;
        this.code = c;
    }

    public int getQuant(){
        return this.quant;
    }
    public String getCode(){
        return this.code;
    }

    @Override
    public String toString() {
        // toString() override
        return name() + " (" + quant+ ", "+ code + ")";
    }
}


public class EnumConstructor {
    public static void main(String[] args){
        CoffeeSize cs = CoffeeSize.MEDIUM;
        System.out.println("My coffee is " + cs );

        for (CoffeeSize size : CoffeeSize.values()) {
            System.out.println(size + ": " + size.getQuant() + " oz");
        }

    }
}
