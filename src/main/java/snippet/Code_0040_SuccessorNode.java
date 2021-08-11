package snippet;

// 特殊的树结构，包含父节点的指针
//返回二叉树的后继节点
//二叉树结构如下定义：
//Class Node {
// V value;
// Node left;
// Node right;
// Node parent;
//}
//给你二叉树中的某个节点，返回该节点中序遍历的后继节点
public class Code_0040_SuccessorNode {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int v) {
            value = v;
        }
    }

    // 节点的右子树不为空，则为右子树的最左节点
    // 如果右子树为空
    // 当前节点如果是父节点的左孩子，则后继节点是其父节点
    // 当前节点如果是其父节点的右孩子，则继续找当前节点父节点的父节点
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else { // 无右子树
            Node parent = node.parent;
            while (parent != null && parent.right == node) { // 当前节点是其父亲节点右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
