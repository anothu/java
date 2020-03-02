
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPCITY = 10;
	private int currentSize;
	private AnyType[] array;

	public BinaryHeap() {
		this(DEFAULT_CAPCITY);
	}

	public BinaryHeap(int capcity) {
		Integer a = 1;
		Integer b = 2;
		a.compareTo(b);
		currentSize = 0;
		array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];
	}

	private void percolateDown(int hole) {
		int child;
		AnyType tmp = array[hole];
		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			// child在hole的左下方，compare看作-号
			if (child != currentSize && array[child + 1].compareTo(array[child]) < 0)
				child++;
			if (array[child].compareTo(tmp) < 0)
				array[hole] = array[child];
			else
				break;
		}
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	public BinaryHeap(AnyType[] items) {
		currentSize = items.length;
		array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];
		int i = 1;
		for (AnyType item : items)
			array[i++] = item;
		buildHeap();
	}

	private void enlargeArray(int newSize) {
		AnyType[] old = array;
		array = (AnyType[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++)
			array[i] = old[i];
	}

	public void insert(AnyType x) {
		if (currentSize == array.length - 1)
			enlargeArray(array.length * 2 + 1);
		int hole = ++currentSize;
		for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
	}

	private boolean isEmpty() {
		return currentSize == 0;
	}

	public AnyType findMin() {
		if (isEmpty())
			throw new NullPointerException();
		return array[1];
	}

	public AnyType deleteMin() {
		if (isEmpty())
			throw new NullPointerException();
		AnyType minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);
		return minItem;
	}

	public void makeEmpty() {
		currentSize = 0;
	}

}
