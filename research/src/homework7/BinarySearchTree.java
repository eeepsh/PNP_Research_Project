package homework7;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BinarySearchTree {

    static String output = "";

    public static class TreeNode // an inner class of class BinarySearchTree, for binary trees
    {

        // 3 private instance variables
        private int data;
        private TreeNode leftLink;
        private TreeNode rightLink;

       
        public TreeNode() {
            data = 0;
            leftLink = rightLink = null;
        }

        public TreeNode(int newData, TreeNode newLeftLink, TreeNode newRightLink) {
            data = newData;
            leftLink = newLeftLink;
            rightLink = newRightLink;
        }
    }

    private TreeNode root;
    private TreeNode parent;
    private int parentLink;

   
    public BinarySearchTree() {
        root = parent = null;
        parentLink = 0;
    }

    
    public BinarySearchTree(TreeNode rootNode) {
        parent = null;
        parentLink = 0;
        root = copyBST(rootNode);
    }

 
    public TreeNode copyBST(TreeNode rootNode) {
        if (rootNode == null) {
            return rootNode;
        }

        
        TreeNode leftChild = copyBST(rootNode.leftLink);
        TreeNode rightChild = copyBST(rootNode.rightLink);
        return new TreeNode(rootNode.data, leftChild, rightChild);
    }

   
    public void add(int item) {
        root = insertInSubtree(item, root);
    }

    //Display binary tree elements in sorted order
    public void showInOrder() {
    	
        showElementsInOrder(root);
        System.out.println();
        //showElementsInSubtree(root);
    }

    //Display binary tree elements in pre order
    public void showPreOrder() {
        showElementsInPreOrder(root);
      
    }

    //Display binary tree elements in post order
    public void showPostOrder() {
        showElementsInPostOrder(root);
       // showElementsInPreOrder(root);
    }

  
    private static void showElementsInOrder(TreeNode treeRoot) {//Uses inorder traversal.
        if (treeRoot != null) {
            showElementsInOrder(treeRoot.leftLink);
            System.out.print(treeRoot.data + " ");
            System.out.println(treeRoot.data + " ");
            showElementsInOrder(treeRoot.rightLink);
        }
        //else do nothing. Empty tree has nothing to display.
    }

   
    private static void showElementsInPreOrder(TreeNode treeRoot) {//Uses preorder traversal.
        if (treeRoot != null) {
            System.out.println(treeRoot.data + " ");
            System.out.print(+treeRoot.data + " ");
            showElementsInPreOrder(treeRoot.leftLink);
            showElementsInPreOrder(treeRoot.rightLink);
        }
    }

    
    private static void showElementsInPostOrder(TreeNode treeRoot) {//Uses postorder traversal.
        if (treeRoot != null) {
        	showElementsInPostOrder(treeRoot.leftLink);
            showElementsInPostOrder(treeRoot.rightLink);
            System.out.print(treeRoot.data + " ");
            System.out.println(treeRoot.data + " ");
        }
    }

   
    public static TreeNode insertInSubtree(int item, TreeNode subTreeRoot) {
        if (subTreeRoot == null) {
            return new TreeNode(item, null, null);
        } else if (item < subTreeRoot.data) {
            subTreeRoot.leftLink = insertInSubtree(item, subTreeRoot.leftLink);
            return subTreeRoot;
        } else //item >= subTreeRoot.data
        {
            subTreeRoot.rightLink = insertInSubtree(item, subTreeRoot.rightLink);
            return subTreeRoot;
        }
    }

    public TreeNode replace(TreeNode target) {
        parent = target.rightLink;
        TreeNode current = parent.leftLink;
        while (current.leftLink != null) {
            parent = current;
            current = current.leftLink;
        }
        return current;
    }//	end of replace()

   
    public TreeNode isInSubtree(int oldItem) {
        parent = null;  //assume no parent
        parentLink = 0; //assume no parent
        TreeNode current = root;
        if (current == null) {
            return current; //not found as binary tree is empty.
        }
        while (current != null) {
            if (current.data == oldItem) {
                return current;
            }
            if (current.data > oldItem) {
                parent = current;
                current = current.leftLink;
                parentLink = 1;
            } else // current.data < oldItem
            {
                parent = current;
                current = current.rightLink;
                parentLink = 2;
            }
        }
        return current; // must be null as matching target not found.
    }   //end of isInSubtree()
    ///////////////////////////////////////////////

    
    public boolean remove(int oldItem) {
        TreeNode t, r, n, rt, lt;
        t = isInSubtree(oldItem);

        if (t == null) {
            return false; //item not found
        }
        //else item is found in binary tree. Proceed to remove it

        rt = t.rightLink;
        lt = t.leftLink;
        if (parentLink != 0) //node is not root, it has a parent
        {
            if (lt == null && rt == null) {
                if (parentLink == 1) {
                    parent.leftLink = null;
                } else if (parentLink == 2) {
                    parent.rightLink = null;
                }
                return true;
            }

            if (lt != null && rt == null)
            {
                if (parentLink == 1) {
                    parent.leftLink = lt;
                } else if (parentLink == 2) {
                    parent.rightLink = lt;
                }
                return true;
            }
            if (lt == null && rt != null) 
            {
                if (parentLink == 1) {
                    parent.leftLink = rt;
                } else if (parentLink == 2) {
                    parent.rightLink = rt;
                }
                return true;
            }
        }
        // else the node has left and right node or it is the root
        if (rt == null && lt == null) {
            root = null;
            return true;
        }
        if (rt == null && lt != null) {
            root = lt;
            return false;
        }
        
        n = rt;	
        if (n.leftLink == null) 
        {
            t.data = n.data;	
            rt = n.rightLink;
            return true;
        }
        
        r = replace(t);
        //copy data of replaced node into target node
        t.data = r.data;
        parent.leftLink = r.rightLink;
        return true;
    }

    public static void main(String[] args) {
        String output = "";
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileOutputStream("program10.out"));
        } catch (FileNotFoundException ex) {
            System.out.println("Output file not found");
            System.exit(0);
        }

        BinarySearchTree tree = new BinarySearchTree();
        int Array[] = {3333, 44, 2222, 444, 555, 999, 88, 234, 654, 1234, 6666, 777};
        int n = 0;
        while (n < 12) {
            tree.add(Array[n++]);
        }

        System.out.println("\t\t\nINORDER TRAVERSAL OF THE INITIAL BINARY SEARCH TREE:::");
        tree.showInOrder();
        System.out.println("\t\t\nPREORDER TRAVERSAL OF THE INITIAL BINARY SEARCH TREE:::");
        tree.showPreOrder();
        BinarySearchTree copied = new BinarySearchTree(tree.root);
        System.out.println("\t\t\n\nPOSTORDER TRAVERSAL OF THE COPIED BINARY SEARCH TREE:::");
        copied.showPostOrder();

        if (tree.remove(999)) {
           System.out.println("\t\t\n\nINORDER TRAVERSAL OF THE REMOVED BINARY SEARCH TREE:::");
            tree.showInOrder();
            System.out.println("\t\t\n\nPROGRAM TERMINATES IN SUCCESS AFTER PROCESSING " + Array.length + " INPUT ELEMENTS.\t\t");

        } else 
        {
            System.out.println("Item not found in tree");
        }

       // outputStream.println(output);
       // outputStream.close();
    }
}
