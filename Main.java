import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create a new HashTable where keys are MyTestingClass objects and values are Student objects
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        // Create a random number generator
        Random rand = new Random();

        // Insert 10,000 random elements into the HashTable
        for (int i = 0; i < 10000; i++) {
            int randomId = rand.nextInt(100000); // Generate random ID
            MyTestingClass key = new MyTestingClass(randomId); // Create new key object
            Student value = new Student("Student" + randomId, randomId); // Create new Student object as value
            table.put(key, value); // Put key-value pair into the table
        }

        // Print the number of elements in each bucket of the HashTable
        System.out.println("Bucket sizes in MyHashTable:");
        table.printBucketSizes();

        // Create a new Binary Search Tree
        BST<Integer, String> tree = new BST<>();

        // Insert elements into the BST
        tree.put(50, "Root");
        tree.put(30, "Left");
        tree.put(70, "Right");
        tree.put(20, "Left-Left");
        tree.put(40, "Left-Right");
        tree.put(60, "Right-Left");
        tree.put(80, "Right-Right");

        // Traverse the BST in in-order and print each key with its value
        System.out.println("\nBST in-order traversal (only keys):");
        for (Integer key : tree) {
            String value = tree.get(key); // Retrieve value by key
            System.out.println("Key: " + key + ", Value: " + value); // Print key and value
        }

        // Print the size of the BST
        System.out.println("\nSize of BST: " + tree.size());
    }
}
