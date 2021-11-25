import java.util.ArrayList;

/**
 * @author Christian Aguirre
 */

/**
 * A generic linked binary tree which inherits from the LinkedConverterTreeInterface.
 * The class uses an external generic TreeNode class parameterized as a String:
 * TreeNode<String>.
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

    private TreeNode<String> root = null;
    private String letter;

    /**
     * Constructor - Calls the buildTree method
     */
    public MorseCodeTree()
    {
        buildTree();
    }

    /**
     * Returns a reference to the root
     * @return Reference to root
     */
    @Override
    public TreeNode<String> getRoot() {
       return this.root;
    }

    /**
     * Sets the root of the MorseCodeTree
     * @param newNode A copy of newNode will be the new root
     */
    @Override
    public void setRoot(TreeNode<String> newNode) {
        this.root = newNode;
    }

    /**
     * Adds element to the correct position in the tree based on the code. This method will
     * call the recursive method addNode
     * @param code the code for the new node to be added, example ".-."
     * @param letter the letter for the corresponding code, example "r"
     * @return the MorseCodeTree with the new node added
     */
    @Override
    public MorseCodeTree insert(String code, String letter) {
       addNode(root, code, letter);



       return this;


    }

    /**
     * This is a recursive method that adds element to the correct position in the tree based on the
     * code. A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right.
     * The code ".-" would be stored as the right child of the left child of the root Algorithm
     * for the recursive method:
     * 1. if there is only one character
     * a. if the character is '.' (dot) store to the left of the current root
     * b. if the character is "-" (dash) store to the right of the current root
     * c. return
     * 2. if there is more than one character
     * a. if the first character is "." (dot) new root becomes the left child
     * b. if the first character is "-" (dash) new root becomes the right child
     * c. new code becomes all the remaining characters in the code (beyond the first character)
     * d. call addNode(new root, new code, letter)
     * @param root the root of the tree for this particular recursive instance of addNode
     * @param code the code for this particular recursive instance of addNode
     * @param letter the data of the new TreeNode to be added
     */


    @Override
    public void addNode(TreeNode<String> root,
                        String code,
                        String letter) {


        /*
         * 1. if there is only one character
         * a. if the character is '.' (dot) store to the left of the current root
         * b. if the character is "-" (dash) store to the right of the current root
         */
       if(code.equals("."))
           root.leftChild = new TreeNode<>(letter);
       if(code.equals("-"))
          root.rightChild = new TreeNode<>(letter);

       /* 2. if there is more than one character
        * a. if the first character is "." (dot) new root becomes the left child
        * * b. if the first character is "-" (dash) new root becomes the right child
        * c. new code becomes all the remaining characters in the code (beyond the first character)
        * d. call addNode(new root, new code, letter)*/
       if(code.length() > 1 && code.charAt(0) == '.')
          addNode(root.leftChild, code.substring(1), letter);
       if(code.length() > 1 && code.charAt(0) == '-')
           addNode(root.rightChild, code.substring(1), letter);


    }


    /**
     * Fetch the data in the tree based on the code
     * This method will call the recursive method fetchNode
     * @param code the code that describes the traversals to retrieve the string (letter)
     * @return the string (letter) that corresponds to the code
     */
    @Override
    public String fetch(String code) {

        return fetchNode(root, code);
    }

    /**
     * This is the recursive method that fetches the data of the TreeNode that corresponds with
     * the code A '.' (dot) means traverse to the left. A "-" (dash) means traverse to the right.
     * The code ".-" would fetch the data of the TreeNode stored as the right child of the
     * left child of the root
     * @param root the root of the tree for this particular recursive instance of addNode
     * @param code the code for this particular recursive instance of fetchNode
     * @return the string (letter) corresponding to the code
     */
    @Override
    public String fetchNode(TreeNode<String> root, String code) {


        //If the first nodes equal "." and "-" they are the first left and right child of the tree
        if(code.equals("."))
            letter = root.leftChild.getData();
        if(code.equals("-"))
            letter = root.rightChild.getData();
        //This is the recursive method that fetches the data of the TreeNode that corresponds
        // with the code
        //A '.' (dot) means traverse to the left.
        //A "-" (dash) means traverse to the right.
        //base case
        if (code.length() > 1 && code.charAt(0) == '.')
            fetchNode(root.leftChild, code.substring(1));
        if (code.length() > 1 && code.charAt(0) == '-')
            fetchNode(root.rightChild, code.substring(1));


        return letter;

    }

    /**
     * This operation is not supported in the MorseCodeTree
     * @param data data of node to be deleted
     * @return reference to the current tree
     * @throws UnsupportedOperationException if operation is not supported
     */

    @Override
    public MorseCodeTree delete(String data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Delete method is not supported on this operation");

    }


    /**
     * This operation is not supported in the MorseCodeTree
     * @return reference to the current tree
     * @throws UnsupportedOperationException If operation is not supported
     */
    @Override
    public MorseCodeTree update() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Update method is not supported on this operation");
    }

    /**
     * This method builds the MorseCodeTree by inserting the nodes of the tree level by level
     * based on the code. The root will have a value of
     * "" (empty string)
     * level one: insert(".", "e");
     * insert("-", "t");
     * level two: insert("..", "i");
     * insert(".-", "a");
     * insert("-.", "n");
     * insert("--", "m"); etc.
     * Look at the tree and the table of codes to letter in the assignment description.
     */
    @Override
    public void buildTree() {
        /*
                            root
                   /                         \
                  e                           t
              /        \                /             \
             i           a               n              m
            / \        /    \       /      \          /  \
           s     u     r      w     d        k        g     o
         /  \   / \   /  \   / \   / \      / \      / \   / \
         h  v  f     l      p  j   b  x    c  y     z   q
         */
        root = new TreeNode<>("");

        //letters with one character morse code
        insert(".", "e");
        insert("-", "t");

        //letters with two character morse code
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");

        //letters with three character morse code
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");

        //letters with four character morse code
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");

    }

    /**
     * Returns an ArrayList of the items in the linked Tree in LNR(Inorder) Traversal order
     * Used for testing to make sure tree is built correctly
     * @return an ArrayList of the items in the linked Tree
     */
    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> tree = new ArrayList<>();

        LNRoutputTraversal(root, tree);

        return tree;
    }

    /**
     * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
     * @param root the root of the tree for this particular recursive instance
     * @param list the ArrayList that will hold the contents of the tree in LNR order
     */

    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        //Recursively traverse the whole tree in order,
        //As long as the root is not null then traverse from the left of the tree to the root
        //of the tree and ending on the right side
        if(root != null){
            LNRoutputTraversal(root.leftChild, list);
            list.add(root.getData());
            LNRoutputTraversal(root.rightChild, list);
        }
    }
}
