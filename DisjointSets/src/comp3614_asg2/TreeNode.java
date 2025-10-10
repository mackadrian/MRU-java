package comp3614_asg2;

/**
 * Class representing a node in the Union-Find forest
 */
public class TreeNode {
	
    private TreeNode parent;
    private String element;
    private int root;
    private int index;
    private int rank;

    public TreeNode(String element, int index) {
        this.element = element;
        this.index = index;
        this.rank = 0;
        this.parent = this;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getElement() {
        return element;
    }

    public int getIndex() {
        return index;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public int getRoot() {
        return root;
    }
}
