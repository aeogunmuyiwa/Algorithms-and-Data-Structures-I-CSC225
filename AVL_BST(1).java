/***ADEBAYO OGUNMUYIWA
 CSC 225 V00837486*/
public class AVL_BST{
	public static boolean checkAVL(BST b){
		//TO DO: write your code;
		int result = b.checker(b.root);
		if(result > 0){
			return true;
		}
		return false;
	}

	public static BST createBST(int[] a){
		//TO DO: write your code;
		 BST tree = new BST();
    for(int i = 0 ; i < a.length; i ++){
      tree.insert(a[i]);
       

    }
    
		return tree;
	}
	public static void main(String[] args){
		int[] A = {82,85,153,195,124,66,200,193,185,243,73,153,76};
		BST b = createBST(A);
		System.out.println(checkAVL(b));
	}
}

class BST{
	/****create node*****/
	public static class Node{
		public Integer value;
		public Node parent;
		public Node left;
		public Node right;
		public Node (Integer x,Node y,Node a,Node b){
			this.parent = y;
			this.value = x;
			this.left = a;
			this.right= b;
		}
		public boolean isLeaf() {
                return left == null && right == null;
    }
	}
	public Node root;
	public int size;
	 Node createNode(int x,Node y,Node a,Node b){
		return new Node(x,y,a,b);
	}

	/**********inserting element into the tree***/
	public Node insert(int x){
		if(root == null){

			root = createNode(x, null, null, null);
      size++;
      return root;
    }

        Node insertParentNode = null;
        Node searchTempNode = root;
        while (searchTempNode != null && searchTempNode.value != null) {
            insertParentNode = searchTempNode;
            if (x < searchTempNode.value) {
                searchTempNode = searchTempNode.left;
            } else {
                searchTempNode = searchTempNode.right;
            }
        }

        Node newNode = createNode(x, insertParentNode, null, null);
        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }

        size++;
        return newNode;
	}
	/***determine if the height is balanced ****/
	public  int checker(Node root){
		if(root == null){
			return 0;
		}
		int left_checker =checker(root.left);
		int right_checker = checker(root.right);
		if(left_checker == -1){
			return -1;
		}
		if(right_checker == -1){
			return -1;
		}
		int difference =left_checker - right_checker;
		if (Math.abs(difference ) > 1){
			return -1;
		} return 1+Math.max(left_checker,right_checker);
	}


    


}
