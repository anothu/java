public class AvlTree<T extends Comparable<? super T>> {
	private static final int ALLOWED_IMBALANCE = 1;
	private AvlNode<T> root;

	private static class AvlNode<T> {
		T element;
		AvlNode<T> left;
		AvlNode<T> right;
		int height;

		AvlNode(T theElement) {
			this(theElement, null, null);
		}

		AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
	}

	public static void main(String[] args) throws Exception {

	}

	public AvlTree() {
		root = null;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	public void remove(T x) {
		root = remove(x, root);
	}

	private AvlNode<T> remove(T x, AvlNode<T> t) {
		if (t == null)
			return t;

		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {
			t.left = remove(x, t.left);
		} else if (compareResult > 0) {
			t.right = remove(x, t.right);
		} else if (t.left != null && t.right != null) {
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else {
			t = (t.left != null) ? t.left : t.right;
		}

		return balance(t);
	}

	public T findMin() throws Exception {
		if (isEmpty()) {
			throw new Exception("The AVL tree is empty!");
		}
		return findMin(root).element;
	}

	public T findMax() throws Exception {
		if (isEmpty()) {
			throw new Exception("The AVL tree is empty!");
		}
		return findMax(root).element;
	}

	public boolean contains(T x) {
		return contains(x, root);
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void printTree() {
		if (isEmpty()) {
			System.out.println("The AVL tree is empty!");
		} else {
			printTree(root);
		}
	}

	private AvlNode<T> balance(AvlNode<T> t) {
		if (t == null) {
			return t;
		}

		if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
			if (height(t.left.left) >= height(t.left.right)) {
				t = rotateWithLeftChild(t);
			} else {
				t = doubleRotateWithLeftChild(t);
			}
		} else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
			if (height(t.right.right) >= height(t.right.left)) {
				t = rotateWithRightChild(t);
			} else {
				t = doubleRotateWithRightChild(t);
			}
		}

		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	public void checkBalance() {
		checkBalance(root);
	}

	private int checkBalance(AvlNode<T> t) {
		if (t == null) {
			return -1;
		}

		if (t != null) {
			int leftHeight = checkBalance(t.left);
			int rightHeight = checkBalance(t.right);
			if (Math.abs(height(t.left) - height(t.right)) > 1 || height(t.left) != leftHeight
					|| height(t.right) != rightHeight) {
				System.out.println("OOPS!!");
			}
		}

		return height(t);
	}

	private AvlNode<T> insert(T x, AvlNode<T> t) {
		if (t == null) {
			return new AvlNode<>(x, null, null);
		}

		int compareResult = x.compareTo(t.element);
		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {
		}

		return balance(t);
	}

	private AvlNode<T> findMin(AvlNode<T> t) {
		if (t == null)
			return t;

		while (t.left != null) {
			t = t.left;
		}
		return t;
	}

	private AvlNode<T> findMax(AvlNode<T> t) {
		if (t == null)
			return t;

		while (t.right != null) {
			t = t.right;
		}
		return t;
	}

	private boolean contains(T x, AvlNode<T> t) {
		while (t != null) {
			int compareResult = x.compareTo(t.element);
			if (compareResult < 0) {
				t = t.left;
			} else if (compareResult > 0) {
				t = t.right;
			} else { // Match.
				return true;
			}
		}

		return false; // No match.
	}

	private void printTree(AvlNode<T> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	private int height(AvlNode<T> t) {
		return t == null ? -1 : t.height;
	}

	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
		AvlNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
		AvlNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}

	private AvlNode<T> doubleRotateWithLeftChild(AvlNode<T> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}

	private AvlNode<T> doubleRotateWithRightChild(AvlNode<T> k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}
}
