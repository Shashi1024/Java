package Codes.Concurrency;

public class Interruption {
    public static void main(String[] args){

        Thread t = new Thread(()->{
            try{
                Thread.currentThread().interrupt();
                System.out.println("try entered");
                Thread.sleep(100);
                // Thread.currentThread().interrupt();
                System.out.println("try exit");
            }catch(Exception e){
                System.out.println("Catch enter");
                Thread.interrupted();
                System.out.println("Catch exit");
            }
        });

        t.start();
        try{
            t.join();
            System.out.println("try join");
        }
        catch(Exception e){
            System.out.println(" catch join ");
        }
        
    }
}
