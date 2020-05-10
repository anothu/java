package 八数码问题;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
	private final static int[] target = { 1, 2, 3, 8, 0, 4, 7, 6, 5 };
	static LinkedList<openElement> open = new LinkedList<Solution.openElement>();

	static class openElement {
		public int[] matrix;
		public int difference;
		public int depth;
		public int zeroLocation;
		public int cost;
		public openElement father;

		public openElement() {
		}
	}

	/** 寻找0在数组中的位置 **/
	private static int zeroLocaton(int a[]) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 0)
				return i;
		}
		return -1;
	}

	/** 初始化 */
	private static void init(int[] input) {
//		for (int i = 0; i < input.length; i++) {
//			System.out.print("输入第" + i + "个数");
//			input[i] = new Scanner(System.in).nextInt();
//		}
		for (int i = 0; i < 9; i++) {
			hashMap.put(target[i], i);
		}
		showMatrix(input);
	}

	/** 显示八数码现在的状态 */
	private static void showMatrix(int[] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(matrix[i] + " ");
			if (i == 2 || i == 5)
				System.out.println();
		}
	}

	static int f = 1;

	/** 显示open表的情况 */
	private static void showOpen() {
		// int flag = 0;

		for (openElement o : open) {
			System.out.println("============open" + f + "表中的元素===============");
			showMatrix(o.matrix);
			System.out.println();
			System.out.println("differentNum: " + (o.difference));
			System.out.println("depth: " + o.depth);
			System.out.println("cost: " + (o.depth + o.difference));
			System.out.println();
		}
		f++;
	}

	/** 八数码中不在对应位置的数的个数 */
	private static int differentNum(int[] matrix) {
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != target[i])
				count++;
		}
		return count;
	}

	/** 计算逆序数 */
	private static int Reverseorder(int[] matrix) {
		int temp[] = { matrix[0], matrix[1], matrix[2], matrix[5], matrix[8], matrix[7], matrix[6], matrix[3],
				matrix[4] };
		int count = 0;
		for (int i = 0; i < matrix.length - 1; i++) {
			if (temp[i] == 0)
				continue;
			for (int j = i + 1; j < matrix.length; j++) {
				if (temp[j] < temp[i] && temp[j] != 0)
					count++;
			}
		}
		return count;
	}

	/** 交换数组中的两个数 */
	private static int change(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		return j;
	}

	/** 插入新数据temp到op中：启发式函数1 */
	private static boolean insertTemp(openElement temp, LinkedList<openElement> op) {
		// 当前节点与目标节点格局相比，位置不符的数字个数
		int cost = temp.depth + temp.difference;
		temp.cost = cost;
		for (int i = 0; i < op.size(); i++) {
			openElement oe = op.get(i);
			if (cost <= oe.depth + oe.difference) {
				op.add(i, temp);
				return true;
			}
		}
		op.addLast(temp);
		return true;
	}

	private static int getRightLocation(int key) {
		return hashMap.get(key);
	}

	private static int f2(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			count += Math.abs(i - getRightLocation(a[i])) % 3;
		}
		return count;
	}

	/** 插入新数据temp到op中：启发式函数2 */
	private static boolean insertTemp2(openElement temp, LinkedList<openElement> op) {
		// 当前节点与目标节点格局相比，位置不符的数字移动到目标节点中对应位置的最短距离之和
		int cost = f2(temp.matrix);
		temp.cost = cost;
		for (int i = 0; i < op.size(); i++) {
			openElement oe = op.get(i);
			if (cost <= oe.cost) {
				op.add(i, temp);
				return true;
			}
		}
		op.addLast(temp);
		return true;
	}

	/** 插入新数据temp到op中：启发式函数3 */
	private static boolean insertTemp3(openElement temp, LinkedList<openElement> op) {
		// 当前节点与目标节点格局相比，位置不符的数字移动到目标节点中对应位置的最短距离之和
		int cost = 3 * Reverseorder(temp.matrix);
		temp.cost = cost;
		for (int i = 0; i < op.size(); i++) {
			openElement oe = op.get(i);
			if (cost <= oe.cost) {
				op.add(i, temp);
				return true;
			}
		}
		op.addLast(temp);
		return true;
	}

	/** 插入新数据temp到op中：启发式函数4 */
	private static boolean insertTemp4(openElement temp, LinkedList<openElement> op) {
		// 当前节点与目标节点格局相比，位置不符的数字移动到目标节点中对应位置的最短距离之和
		int cost = 3 * Reverseorder(temp.matrix) + f2(temp.matrix);
		temp.cost = cost;
		for (int i = 0; i < op.size(); i++) {
			openElement oe = op.get(i);
			if (cost <= oe.cost) {
				op.add(i, temp);
				return true;
			}
		}
		op.addLast(temp);
		return true;
	}

	/** 八数码下一次移动 */
	private static boolean move() {
		while (true) {
			if (open.isEmpty())
				return false;
			showOpen();
			openElement o = open.removeFirst();
			if (o.depth > 10000)
				return false;
			int lastZeroLocation = o.father != null ? o.father.zeroLocation : -100;
			int matrix[] = o.matrix;
			int nowZeroLocation = o.zeroLocation;
			if (nowZeroLocation - 1 != lastZeroLocation && nowZeroLocation != 0 && nowZeroLocation != 3
					&& nowZeroLocation != 6) {
				openElement temp = new openElement();
				int[] mLeft = matrix.clone();
				change(mLeft, nowZeroLocation, nowZeroLocation - 1);
				int different = differentNum(mLeft);
				if (different == 0) {
					System.out.println("最少需要通过" + (o.depth + 1) + "步才能到达终点");
					return true;
				}
				temp.zeroLocation = nowZeroLocation - 1;
				temp.matrix = mLeft;
				temp.father = o;
				temp.difference = different;
				temp.depth = o.depth + 1;
				insertTemp4(temp, open);
			}
			if (nowZeroLocation + 1 != lastZeroLocation && nowZeroLocation != 2 && nowZeroLocation != 5
					&& nowZeroLocation != 8) {
				openElement temp = new openElement();
				int[] mLeft = matrix.clone();
				change(mLeft, nowZeroLocation, nowZeroLocation + 1);
				int different = differentNum(mLeft);
				if (different == 0) {
					System.out.println("最少需要通过" + (o.depth + 1) + "步才能到达终点");
					return true;
				}
				temp.difference = different;
				temp.zeroLocation = nowZeroLocation + 1;
				temp.matrix = mLeft;
				temp.father = o;
				temp.depth = o.depth + 1;
				insertTemp4(temp, open);
			}
			if (nowZeroLocation - 3 != lastZeroLocation && nowZeroLocation != 0 && nowZeroLocation != 1
					&& nowZeroLocation != 2) {
				openElement temp = new openElement();
				int[] mLeft = matrix.clone();
				change(mLeft, nowZeroLocation, nowZeroLocation - 3);
				int different = differentNum(mLeft);
				if (different == 0) {
					System.out.println("最少需要通过" + (o.depth + 1) + "步才能到达终点");
					return true;
				}
				temp.difference = different;
				temp.zeroLocation = nowZeroLocation - 3;
				temp.matrix = mLeft;
				temp.father = o;
				temp.depth = o.depth + 1;
				insertTemp4(temp, open);
			}
			if (nowZeroLocation + 3 != lastZeroLocation && nowZeroLocation != 6 && nowZeroLocation != 7
					&& nowZeroLocation != 8) {
				openElement temp = new openElement();
				int[] mLeft = matrix.clone();
				change(mLeft, nowZeroLocation, nowZeroLocation + 3);
				int different = differentNum(mLeft);
				if (different == 0) {
					System.out.println("最少需要通过" + (o.depth + 1) + "步才能到达终点");
					return true;
				}
				temp.difference = different;
				temp.zeroLocation = nowZeroLocation + 3;
				temp.matrix = mLeft;
				temp.father = o;
				temp.depth = o.depth + 1;
				insertTemp4(temp, open);
			}

		}
	}

	/** 判断逆序数奇偶性 */
	private static boolean judge(int matrix[]) {
		int a = Reverseorder(matrix);
		int b = Reverseorder(target);
		System.out.println("测试数组逆序数:" + a);
		System.out.println("target逆序数：" + b);
		return a % 2 == b % 2;
	}

	/** main */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int matrix[] = { 2, 8, 0, 1, 3, 5, 4, 7, 6 };
		init(matrix);
		if (!judge(matrix)) {
			System.out.println("逆序数验证失败");
			return;
		} else {
			System.out.println("逆序数验证成功");
		}
		openElement o = new openElement();
		int different = differentNum(matrix);
		if (different == 0) {
			System.out.println("测试数组本身满足要求！");
			return;
		}
		o.depth = 0;
		o.father = null;
		o.matrix = matrix;
		o.difference = different;
		o.zeroLocation = zeroLocaton(matrix);
		// 1
		// o.cost = o.depth + o.difference;
		// 2
		// o.cost = f2(matrix);
		// 3
		// o.cost = 3 * Reverseorder(matrix);
		// 4
		o.cost = 3 * Reverseorder(matrix) + f2(matrix);
		open.add(o);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		move();
		// System.out.println(o.cost);
		System.out.println("总共耗时：" + (System.currentTimeMillis() - start) + "毫秒");
	}
}
