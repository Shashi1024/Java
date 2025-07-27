package Codes.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student>{
    private int rolNo;
    private String name;
    private int age;

    public Student(int rolNo, String n, int a){
        this.rolNo = rolNo;
        this.name = n;
        this.age = a;
    }

    public int getRollNo(){
        return this.rolNo;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public int compareTo(Student ci){
        return this.rolNo - ci.rolNo;
    }

    @Override
    public String toString(){
        return "Name: "+this.name+" rollNo: "+this.rolNo+" Age: "+this.age;
    }

}


public class ComparableInterface{
    public static void main(String[] args){
        List<Student> students = new ArrayList<>();
        students.add(new Student(101, "Vijay", 23));
        students.add(new Student(106, "Ajay", 27));
        students.add(new Student(105, "Jai", 21));

        System.out.println("List of ComparableInterfaces before sorting:");
        for (Student s : students) {
            System.out.println(s);
        }

        
        Collections.sort(students);

        System.out.println("\nList of ComparableInterfaces after sorting by roll number (natural order):");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}