class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class QueueManager {
    Node front, rear;

    void enqueue (String data) {
        Node newNode = new Node(data);
        if (rear == null){
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public String dequeque() {
        if (front == null){
            System.out.println("Belum ada antrian");
            return  null;
        }

        String removedData  = front.data;
        front = front.next;
        if (front == null){
            rear = null;
        }

        System.out.println(removedData + " Telah dihapus dari antrian ");
        return removedData;
    }

    void display() {
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.print("Antrian habis\n");
    }
}
