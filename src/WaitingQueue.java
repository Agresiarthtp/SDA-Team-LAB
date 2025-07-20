public class WaitingQueue {
    private Member front;
    private Member rear;

    public WaitingQueue() {
        this.front = null;
        this.rear = null;
    }

    // Menambahkan anggota ke antrian
    public void enqueue(String memberId, String name, String requestedBookId) {
        Member newMember = new Member(memberId, name, requestedBookId);

        if (rear == null) {
            front = rear = newMember;
            System.out.println(name + " (ID: " + memberId + ") berhasil ditambahkan ke antrian untuk buku ID: " + requestedBookId);
            return;
        }

        rear.setNext(newMember);
        rear = newMember;
        System.out.println(name + " (ID: " + memberId + ") berhasil ditambahkan ke antrian untuk buku ID: " + requestedBookId);
    }

    // Melayani anggota (menghapus dari antrian)
    public Member dequeue() {
        if (front == null) {
            System.out.println("Antrian kosong");
            return null;
        }

        Member servedMember = front;
        front = front.getNext();

        if (front == null) {
            rear = null;
        }

        System.out.println(servedMember.getName() + " telah dilayani dan dihapus dari antrian");
        return servedMember;
    }

    // Menampilkan daftar antrian
    public void display() {
        if (front == null) {
            System.out.println("Antrian kosong");
            return;
        }

        System.out.println("\nDaftar Anggota dalam Antrian:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-25s %-15s%n", "No", "ID Anggota", "Nama Anggota", "ID Buku");
        System.out.println("------------------------------------------------------------------");

        Member temp = front;
        int count = 1;
        while (temp != null) {
            System.out.printf("%-5d %-15s %-25s %-15s%n", count, temp.getMemberId(), 
                          temp.getName(), temp.getRequestedBookId());
            temp = temp.getNext();
            count++;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Total anggota dalam antrian: " + (count - 1));
    }

    // Method untuk menganalisis kinerja Queue
    public void analyzePerformance() {
        System.out.println("\nAnalisis Kinerja Queue dalam Sistem Antrian Perpustakaan:");
        System.out.println("1. Kelebihan:\n" +
                         "   - Operasi enqueue dan dequeue sangat efisien dengan kompleksitas O(1)\n" +
                         "   - Implementasi FIFO (First In First Out) sesuai dengan antrian dunia nyata\n" +
                         "   - Ideal untuk pelayanan perpustakaan yang adil (first come, first served)");
        System.out.println("2. Kekurangan:\n" +
                         "   - Tidak dapat melayani anggota berdasarkan prioritas tertentu\n" +
                         "   - Tidak mendukung akses acak ke anggota di tengah antrian\n" +
                         "   - Pencarian anggota dalam antrian memerlukan waktu O(n)");
    }
}
