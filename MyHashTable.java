// Custom implementation of a HashTable with separate chaining
public class MyHashTable<K, V> {

    // Inner class representing a node in a chain (linked list)
    private class HashNode<K, V> {
        private K key;        // Key of the node
        private V value;      // Value associated with the key
        private HashNode<K, V> next; // Reference to the next node

        // Constructor for HashNode
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        // toString method for easier printing
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray; // Array of chains (buckets)
    private int M = 11; // Default number of buckets
    private int size;   // Number of key-value pairs stored

    // Default constructor initializing with default bucket size
    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    // Constructor with custom number of buckets
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    // Hash function to compute index for a key
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M; // Always positive modulo
    }

    // Insert a key-value pair into the hash table
    public void put(K key, V value) {
        int index = hash(key); // Compute index
        HashNode<K, V> head = chainArray[index];

        // Check if key already exists; if yes, update the value
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value; // Update existing value
                return;
            }
            head = head.next;
        }

        // Insert a new node at the beginning of the chain
        size++;
        head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = head;
        chainArray[index] = newNode;
    }

    // Retrieve value by key
    public V get(K key) {
        int index = hash(key); // Compute index
        HashNode<K, V> head = chainArray[index];

        // Search through the chain
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value; // Key found
            }
            head = head.next;
        }

        return null; // Key not found
    }

    // Remove a key-value pair by key
    public V remove(K key) {
        int index = hash(key); // Compute index
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        // Traverse the chain
        while (head != null) {
            if (head.key.equals(key)) {
                break; // Key found
            }
            prev = head;
            head = head.next;
        }

        if (head == null) return null; // Key not found

        size--;

        if (prev != null) {
            prev.next = head.next; // Bypass the deleted node
        } else {
            chainArray[index] = head.next; // Delete first node
        }

        return head.value; // Return deleted value
    }

    // Check if a value exists in the table
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true; // Value found
                }
                head = head.next;
            }
        }
        return false; // Value not found
    }

    // Retrieve the key associated with a given value
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key; // Key found
                }
                head = head.next;
            }
        }
        return null; // Value not found
    }

    // Print the size (number of elements) of each bucket
    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                count++;
                head = head.next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
    }
}
