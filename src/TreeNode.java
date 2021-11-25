/**
 * @author Christian Aguirre
 */
public class TreeNode<T> {

    TreeNode<T> leftChild;
    TreeNode<T> rightChild;
    T data;

    /**
     * Create a new TreeNode with left and right child set to null
     * and data set to the dataNode
     * @param dataNode the data to be stored in the TreeNode
     */
    public TreeNode(T dataNode){
        this.leftChild = null;
        this.rightChild = null;
        this.data = dataNode;

    }

    /**
     * Used for making deep copies
     * @param node Node to make copy of
     */

    public TreeNode(TreeNode<T> node)
    {
        this.leftChild = new TreeNode<>(null);
        this.rightChild = new TreeNode<>(null);
        new TreeNode<>(node);
    }

    /**
     * Return the data within this TreeNode
     * @return The data within the TreeNode
     */

    public T getData()
    {
        return data;
    }




}
