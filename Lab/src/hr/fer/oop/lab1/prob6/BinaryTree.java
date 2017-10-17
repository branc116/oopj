package hr.fer.oop.lab1.prob6;

import java.util.function.BiFunction;

public class BinaryTree<T> {
    private TreeNode<T> root;
    private BiFunction<T, T, Integer> compFunc;
    public BinaryTree(BiFunction<T, T, Integer> compFuncParam) {
        this.compFunc = compFuncParam;
        this.root = new TreeNode<T>();
    }
    private void subTreeInsert(TreeNode<T> tempRoot, T data) {
        if (tempRoot.data == null){
            tempRoot.data = data;
            tempRoot.left = new TreeNode<T>();
            tempRoot.right = new TreeNode<T>();
            return;
        }
        if (compFunc.apply(tempRoot.data, data) <= 0) {
            subTreeInsert(tempRoot.left, data);
            return;
        }
        subTreeInsert(tempRoot.right, data);
        return;
    }
    public void insert(T data) {
        subTreeInsert(this.root, data);
    }
    public Boolean subTreeContainsData(TreeNode<T> tempRoot, T data) {
        if (tempRoot == null || tempRoot.data == null)
            return false;
        Integer compFuncValue = compFunc.apply(tempRoot.data, data);
        if (compFuncValue == 0)
            return true;
        if (compFuncValue < 0)
            return subTreeContainsData(tempRoot.left, data);
        return subTreeContainsData(tempRoot.right, data);
    }

    public Boolean containsData(T data) {
        return subTreeContainsData(this.root, data);
    }
    private Integer sizeOfSubTree(TreeNode<T> tempRoot) {
        if (tempRoot == null || tempRoot.data == null)
            return 0;
        return 1 + sizeOfSubTree(tempRoot.left) + sizeOfSubTree(tempRoot.right);
    }
    public Integer sizeOfTree() {
        return sizeOfSubTree(this.root);
    }
    private void writeTree(TreeNode<T> tempRoot, int depth) {
        if (tempRoot == null || tempRoot.data == null)
            return;
        String space = "";
        for (int i = 0;i < depth - 1;i++){
            space += "|";
        }
        space += "-";
        writeTree(tempRoot.left, depth + 1);
        System.out.printf("%s%n", tempRoot.data.toString());
        writeTree(tempRoot.right, depth + 1);

    }
    public void writeTree() {
        writeTree(this.root, 0);
    }
    public static void main(String[] args) {
        System.out.printf("constructing%n");
        BinaryTree<String> tree = new BinaryTree<String>((str1, str2) -> {
            Integer same = str1.compareTo(str2);
            if (same == 0)
                return 0;
            return str1.length() >= str2.length() ? -1 : 1;
        });
        System.out.printf("constructed%n");
        tree.insert("arg");
        tree.insert("arg");
        tree.insert("arg2");
        for (String arg : args) {
            tree.insert(arg);
        }
        for (String arg : args) {
            System.out.printf("%s is %sin binary tree.%n", arg, !tree.containsData(arg) ? "not " : "");
        
        }
        System.out.printf("Root of tree is %s.%n", tree.root.data);
        System.out.printf("Size of tree is %d.%n", tree.sizeOfTree());
        tree.writeTree();
        return;
    }
}