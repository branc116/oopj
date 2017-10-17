package hr.fer.oop.lab1.prob6;

public  class TreeNode <T> {
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode(T data) {
        this.data = data;
    }
    public TreeNode() {
        data = null;
    }
}