import java.util.Iterator;
import java.util.Stack;

// Custom implementation of a Binary Search Tree (BST) without recursion
public class BST<K extends Comparable<K>, V> implements Iterable<K> {

    // Inner Node class representing each tree node
    private class Node {
        private K key;      // Key of the node
        private V val;      // Value associated with the key
        private Node left;  // Reference to the left child
        private Node right; // Reference to the right child

        // Constructor to initialize the node with key and value
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root; // Root node of the BST
    private int size;  // Number of nodes in the BST

    // Insert a key-value pair into the BST (iteratively)
    public void put(K key, V val) {
        Node newNode = new Node(key, val); // Create a new node
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) { // Go to the left subtree
                if (current.left == null) {
                    current.left = newNode;
                    size++;
                    return;
                }
                current = current.left;
            } else if (cmp > 0) { // Go to the right subtree
                if (current.right == null) {
                    current.right = newNode;
                    size++;
                    return;
                }
                current = current.right;
            } else {
                current.val = val; // Update value if key already exists
                return;
            }
        }
    }

    // Retrieve value by key (iterative)
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val; // Key found
            }
        }
        return null; // Key not found
    }

    // Delete a node by key (iterative)
    public void delete(K key) {
        Node parent = null;
        Node current = root;

        // Search for the node to delete
        while (current != null && !current.key.equals(key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) return; // Key not found

        // Case 1: Node with only one child or no child
        if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;

            if (parent == null) {
                root = child; // Deleting root
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            size--;
        }
        // Case 2: Node with two children
        else {
            Node successorParent = current;
            Node successor = current.right;

            // Find inorder successor (smallest node in right subtree)
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            // Copy successor's key and value to current node
            current.key = successor.key;
            current.val = successor.val;

            // Delete the successor node
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
            size--;
        }
    }

    // Return the number of nodes in the BST
    public int size() {
        return size;
    }

    // Return an iterator for in-order traversal of keys
    @Override
    public Iterator<K> iterator() {
        return new BSTKeyIterator();
    }

    // Inner class for in-order traversal using stack (no recursion)
    private class BSTKeyIterator implements Iterator<K> {
        private Stack<Node> stack;

        // Constructor - push all left nodes onto the stack
        public BSTKeyIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        // Helper method to push all left children
        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        // Check if there is a next node
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Return the next key
        @Override
        public K next() {
            Node node = stack.pop();
            if (node.right != null) {
                pushLeft(node.right);
            }
            return node.key;
        }
    }
}
