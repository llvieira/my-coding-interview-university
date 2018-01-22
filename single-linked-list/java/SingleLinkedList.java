public class SingleLinkedList {
	Node head;
	Node tail;
	int size;

	public SingleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public boolean empty() {
		return size == 0;
	}

	public Object valueAt(int index) {
		return nodeAtIndex(head, index, 0).value;
	}

	private Node nodeAtIndex(Node node, int index, int currentIndex) {
		if (node == null) {
			return null;
		} else if (index == currentIndex) {
			return node;
		} else {
			return nodeAtIndex(node.next, index, currentIndex + 1);
		}
	}

	public void pushFront(Object value) {
		Node newNode = new Node(value, head);
		head = newNode;

		size += 1;
	}

	public Object popFront() {
		size -= 1;

		Object headValue = head.value;
		head = head.next;

		return headValue;
	}

	public void pushBack(Object value) {
		Node newNode = new Node(value, null);
		tail.next = newNode;
		tail = newNode;

		size += 1;
	}

	public Object popBack() {
		size -= 1;

		Object tailValue = tail.value;
		tail = null;

		return tailValue;
	}

	public Object front() {
		return head.value;
	}

	public Object back() {
		return tail.value;
	}

	public void insert(int index, Object value) {
		if (index > size || index < 0) {
			return;
		}

		if (size == 0 && index == 0) { // insert head and tail
			head = new Node(value, null);
			tail = head;
		} else {
			Node nodeBeforeIndex = nodeAtIndex(head, index - 1, 0);
			Node newNode = null;

			if (index == 0) { // change head
				newNode = new Node(value, head);
				head = newNode;
			} else { // insert node
				newNode = new Node(value, nodeBeforeIndex.next);
				nodeBeforeIndex.next = newNode;
			}

			if (index == size) { // change tail
				tail = newNode;
			}
		}

		size += 1;
	}

	public void erase(int index) {
		if (index > size || index < size) {
			return;
		}

		Node nodeBeforeIndex = nodeAtIndex(head, index - 1, 0);
		Node nodeAtIndex = nodeAtIndex(head, index, 0);

		nodeBeforeIndex.next = nodeAtIndex.next;
		nodeAtIndex = null;

		size -= 1;
	}

	public Object valueNFromEnd(int n) {
		int index = size - n;

		return valueAt(index);
	}

	public void reverse() {
		Node current = head;
		Node next = null;
		Node prev = null;

		tail = head;

		while (current != null) {
			if (current.next == null) {
				head = current;
			}

			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
	}

	public void removeValue(Object value) {
		if (head.value == value) {
			head = head.next;
		} else {
			Node nodeBefore = findNodeBefore(value, head);
			Node currentNode = getNodeByValue(value, head);

			nodeBefore.next = currentNode.next;

			if (currentNode == tail) {
				tail = nodeBefore;
			}

			currentNode = null;
		}
	}

	private Node getNodeByValue(Object value, Node node) {
		if (node == null) {
			return null;
		} else if (node.value == value) {
			return node;
		} else {
			return getNodeByValue(value, node.next);
		}
	}

	private Node findNodeBefore(Object value, Node node) {
		if (node == null || node.next == null) {
			return null;
		} else if (node.next.value == value) {
			return node;
		} else {
			return findNodeBefore(value, node.next);
		}
	}

	public void print() {
		print(head);
	} 

	private Node print(Node node) {
		if (node == null) {
			return null;
		} else {
			System.out.println(node.value);
			return print(node.next);
		}
	}
}

class Node {
	Object value;
	Node next;

	public Node(Object value, Node next) {
		this.value = value;
		this.next = next;		
	}
}
