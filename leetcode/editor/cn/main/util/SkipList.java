package util;

import java.util.Random;

/**
 * 不固定层级的跳跃表
 * 参考：http://www.acmerblog.com/skip-list-impl-java-5773.html
 */
public class SkipList<T> {
	private static final double PROBABILITY = 0.5;//向上提升一个的概率
	private SkipListNode<T> head, tail;
	private int nodes;//节点总数
	private int listLevel;//层数
	private Random random;// 用于投掷硬币

	public SkipList() {
		// TODO Auto-generated constructor stub
		random = new Random();
		clear();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SkipList<String> list = new SkipList<String>();
		System.out.println(list);
		list.put(2, "yan");
		list.put(1, "co");
		list.put(3, "feng");
		list.put(1, "cao");//测试同一个key值
		list.put(4, "你");
		list.put(6, "丰");
		list.put(5, "好");
		System.out.println(list);
		System.out.println(list.size());
	}

	/**
	 * 清空跳跃表
	 */
	public void clear() {
		head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
		tail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
		linkByHorizontal(head, tail);
		listLevel = 0;
		nodes = 0;
	}

	public boolean isEmpty() {
		return nodes == 0;
	}

	public int size() {
		return nodes;
	}

	/**
	 * 在最下面一层，找到要插入的位置前面的那个key
	 */
	private SkipListNode<T> findNode(int key) {
		SkipListNode<T> p = head;
		while (true) {
			while (p.right.key != SkipListNode.TAIL_KEY && p.right.key <= key) {
				p = p.right;
			}
			if (p.down != null) {
				p = p.down;
			} else {
				break;
			}

		}
		return p;
	}

	/**
	 * 查找是否存储key，存在则返回该节点，否则返回null
	 */
	public SkipListNode<T> get(int key) {
		SkipListNode<T> p = findNode(key);
		if (key == p.key) {
			return p;
		} else {
			return null;
		}
	}

	/**
	 * 向跳跃表中添加key-value
	 */
	public void put(int k, T v) {
		SkipListNode<T> p = findNode(k);
		//如果key值相同，替换原来的vaule即可结束
		if (k == p.key) {
			p.value = v;
			return;
		}
		SkipListNode<T> q = new SkipListNode<T>(k, v);
		linkByBack(p, q);
		int currentLevel = 0;//当前所在的层级是0
		//抛硬币
		while (random.nextDouble() < PROBABILITY) {
			//如果超出了高度，需要重新建一个顶层
			if (currentLevel >= listLevel) {
				listLevel++;
				SkipListNode<T> p1 = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
				SkipListNode<T> p2 = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
				linkByHorizontal(p1, p2);
				linkByVertical(p1, head);
				linkByVertical(p2, tail);
				head = p1;
				tail = p2;
			}
			//将p移动到上一层
			while (p.up == null) {
				p = p.left;
			}
			p = p.up;

			SkipListNode<T> e = new SkipListNode<T>(k, null);//只保存key就ok
			linkByBack(p, e);//将e插入到p的后面
			linkByVertical(e, q);//将e和q上下连接
			q = e;
			currentLevel++;
		}
		nodes++;//层数递增
	}

	//node1后面插入node2
	private void linkByBack(SkipListNode<T> node1, SkipListNode<T> node2) {
		node2.left = node1;
		node2.right = node1.right;
		node1.right.left = node2;
		node1.right = node2;
	}

	/**
	 * 水平双向连接
	 */
	private void linkByHorizontal(SkipListNode<T> node1, SkipListNode<T> node2) {
		node1.right = node2;
		node2.left = node1;
	}

	/**
	 * 垂直双向连接
	 */
	private void linkByVertical(SkipListNode<T> node1, SkipListNode<T> node2) {
		node1.down = node2;
		node2.up = node1;
	}

	/**
	 * 打印出原始数据
	 */
	@Override
	public String toString() {
		if (isEmpty()) {
			return "跳跃表为空！";
		}
		StringBuilder builder = new StringBuilder();
		SkipListNode<T> p = head;
		while (p.down != null) {
			p = p.down;
		}

		while (p.left != null) {
			p = p.left;
		}
		if (p.right != null) {
			p = p.right;
		}
		while (p.right != null) {
			builder.append(p);
			builder.append("\n");
			p = p.right;
		}

		return builder.toString();
	}

	static class SkipListNode<T> {
		public static final int HEAD_KEY = Integer.MIN_VALUE; // 负无穷
		public static final int TAIL_KEY = Integer.MAX_VALUE; // 正无穷
		public int key;
		public T value;
		public SkipListNode<T> up, down, left, right; // 上下左右 四个指针

		public SkipListNode(int k, T v) {
			key = k;
			value = v;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null) {
				return false;
			}
			if (!(o instanceof SkipListNode<?>)) {
				return false;
			}
			SkipListNode<T> ent;
			try {
				ent = (SkipListNode<T>) o; // 检测类型
			} catch (ClassCastException ex) {
				return false;
			}
			return (ent.key == key) && (ent.value == value);
		}

		@Override
		public String toString() {
			return "key-value:" + key + "-" + value;
		}
	}

}

