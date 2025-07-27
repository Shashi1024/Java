package Codes.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Student{
    private int roll;
    private String name;
    private int age;

    public Student(int r, String n, int a){
        this.roll = r;
        this.name = n;
        this.age = a;
    }

    public int getRoll(){
        return this.roll;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }

    @Override
    public String toString(){
        return "Name: "+this.name+" rollNo: "+this.roll+" Age: "+this.age;
    }
}

class SortByName implements Comparator<Student>{
    @Override
    public int compare(Student a, Student b){
        return a.getName().compareTo(b.getName());
    }
}

class SortByAge implements Comparator<Student>{
    @Override
    public int compare(Student a, Student b){
        return a.getAge()- b.getAge();
    }
}

public class ComparatorInterface {
    public static void main(String[] args){
        List<Student> students = new ArrayList<>();
        students.add(new Student(101, "Vijay", 23));
        students.add(new Student(106, "Ajay", 27));
        students.add(new Student(105, "Jai", 21));

        System.out.println("List of students before sorting:");
        System.out.println(students);

        // Collections.sort(students, new SortByName()); // --> using the above defined comparator methods
        // using lambda expressions 
        // students.sort((a, b) -> a.getName().compareTo(b.getName())); --> one way
        Collections.sort(students, (a, b) -> a.getName().compareTo(b.getName()));
        System.out.println("\nList of students after sorting by name:");
        System.out.println(students);

        Collections.sort(students, new SortByAge());
        // using lambda expressions 
        // students.sort((a, b) -> a.getAge()-b.getAge()); // --> one way
        Collections.sort(students, (a, b) -> a.getAge()-b.getAge());
        System.out.println("\nList of students after sorting by age:");
        System.out.println(students);

        // other syntax
        Collections.sort(students, (a,b) -> {
            return a.getAge()-b.getAge();
        });


        // Utility Methods of Comparator Interface
        // Comparator method chaining using Arrow Syntax (lambda expr)
        Collections.sort(students, Comparator.comparing((Student student) -> student.getRoll()).reversed().thenComparing(student -> student.getName()));

        // using the Method References
        Collections.sort(students, Comparator.comparing(Student::getRoll).reversed().thenComparing(Student::getName));
    }
}
