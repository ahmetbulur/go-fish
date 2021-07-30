
public class SingleLinkedList {

	private Node head;

	public SingleLinkedList() {

		head = null;

	}

	public void add(Object dataToAdd) {
		if (head == null) {
			Node newnode = new Node(dataToAdd);
			head = newnode;
		}

		else if ((int) dataToAdd <= (int) head.getData()) {
			Node newnode = new Node(dataToAdd);
			newnode.setLink(head);
			head = newnode;
		}

		else {
			Node temp = head;
			Node previous = new Node(null);

			while (temp != null && (Integer) dataToAdd >= (Integer) temp.getData()) {
				previous = temp;
				temp = temp.getLink();
			}

			if (temp == null) {
				Node newnode = new Node(dataToAdd);
				previous.setLink(newnode);
			}

			else {
				Node newnode = new Node(dataToAdd);
				newnode.setLink(temp);
				previous.setLink(newnode);
			}
		}
	}

	public int search(Object dataToSearch) {

		int counter = 0;
		Node temp = head;

		while (temp != null) {
			if ((int) temp.getData() == (int) dataToSearch) {

				counter++;

			}
			temp = temp.getLink();
		}
		return counter;

	}

	public void remove(Object dataToRemove) {

		if (head == null)
			System.err.println("Linked List is Empty");
		else {
			while (head != null && (int) head.getData() == (int) dataToRemove)
				head = head.getLink();
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((int) temp.getData() == (int) dataToRemove) {
					prev.setLink(temp.getLink());
					temp = temp.getLink();
				} else {
					prev = temp;
					temp = temp.getLink();
				}
			}

		}

	}

	public int size() {

		Node temp = head;
		int counter = 0;
		while (temp != null) {
			counter++;
			temp = temp.getLink();
		}
		return counter;

	}

	public String display() {

		String output = "";
		Node temp = head;
		while (temp != null) {
			output += temp.getData() + " ";
			temp = temp.getLink();
		}
		return output;
	}

}