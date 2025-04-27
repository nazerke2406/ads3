// A simple class to represent a Student object
public class Student {
    private String name; // Student's name
    private int id;      // Student's unique ID

    // Constructor to initialize a Student with a name and id
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // toString method to provide a string representation of Student objects
    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", id=" + id + '}';
    }
}
