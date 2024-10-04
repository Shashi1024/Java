import java.util.*;
import java.util.regex.*;
public class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=Integer.parseInt(sc.nextLine());
        
        ArrayList<String> l1=new ArrayList<>();
        for(int i=0;i<n;i++){
            l1.add(sc.nextLine());
        }
        String ipv4Pattern="^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}$";
        String ipv6Pattern="^([\\da-fA-F]{1,4})(:[\\da-fA-F]{1,4}){7}$";
        
        Pattern ipv4Regex = Pattern.compile(ipv4Pattern);
        Pattern ipv6Regex = Pattern.compile(ipv6Pattern);
        ArrayList<String> list=new ArrayList<>();
        
        for(String i:l1){
            if(ipv4Regex.matcher(i).matches()){
                list.add("IPv4");
            }
            else if(ipv6Regex.matcher(i).matches()){
                list.add("IPv6");
            }
            else{
                list.add("Invalid");
            }
        }
        System.out.println(list);
    }
}
