// Custom class used as keys for MyHashTable
public class MyTestingClass {
    private int id; // Unique identifier for the object

    // Constructor that initializes the id
    public MyTestingClass(int id) {
        this.id = id;
    }

    // Custom hashCode method to generate hash based on id
    @Override
    public int hashCode() {
        return id * 31 + 7; // Simple hash function based on id
    }

    // Equals method to compare two MyTestingClass objects by id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // If both references are the same
        if (!(o instanceof MyTestingClass)) return false; // If object is not the same type
        MyTestingClass that = (MyTestingClass) o; // Type cast
        return id == that.id; // Compare by id
    }

    // toString method to print MyTestingClass objects
    @Override
    public String toString() {
        return "MyTestingClass{" + "id=" + id + '}';
    }
}
