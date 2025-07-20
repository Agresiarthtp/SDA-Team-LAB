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
        if (front == null) {
            System.out.println("Antrian kosong");
            return;
        }

        Node temp = front;
        System.out.println("\nDaftar Antrian:");
        int urutan = 1;
        while (temp != null) {
            System.out.println(urutan + ". " + temp.data);
            temp = temp.next;
            urutan++;
        }
        System.out.println("Total antrian: " + (urutan - 1));
    }
}
