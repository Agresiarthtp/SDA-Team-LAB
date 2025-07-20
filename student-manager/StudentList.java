import java.util.Scanner;

public class StudentList {
    // Node class untuk linked list
    private static class Node {
        int id;
        String name;
        int grade;
        Node next;

        Node(int id, String name, int grade) {
            this.id = id;
            this.name = name;
            this.grade = grade;
            this.next = null;
        }
    }

    private Node head;

    // Tambah mahasiswa
    public void addStudent(int id, String name, int grade) {
        Node newNode = new Node(id, name, grade);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    // Hapus mahasiswa berdasarkan ID
    public void removeStudent(int id) {
        if (head == null) {
            System.out.println("Daftar kosong.");
            return;
        }

        if (head.id == id) {
            head = head.next;
            System.out.println("Mahasiswa berhasil dihapus.");
            return;
        }

        Node current = head;
        while (current.next != null && current.next.id != id)
            current = current.next;

        if (current.next == null) {
            System.out.println("Mahasiswa dengan NIM " + id + " tidak ditemukan.");
        } else {
            current.next = current.next.next;
            System.out.println("Mahasiswa berhasil dihapus.");
        }
    }

    // Update nilai mahasiswa berdasarkan ID
    public void updateGrade(int id, int newGrade) {
        Node current = head;
        while (current != null) {
            if (current.id == id) {
                current.grade = newGrade;

                System.out.println("Mengupdate nilai mahasiswa (" + current.name + " -> " + current.grade + ")");
                return;
            }
            current = current.next;
        }
        System.out.println("Mahasiswa dengan NIM " + id + " tidak ditemukan.");
    }

    // Tampilkan semua mahasiswa
    public void displayStudents() {
        if (head == null) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }

        System.out.println("\nDaftar Mahasiswa:");
        Node current = head;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". NIM: " + current.id + ", Nama: " + current.name + ", Nilai: " + current.grade);
            current = current.next;
            no++;
        }
        System.out.println();
    }

    public void displayStudentsAfterUpdate() {
        if (head == null) {
            System.out.println("Daftar mahasiswa kosong.");
            return;
        }

        Node current = head;
        int no = 1;
        System.out.println("Daftar Mahasiswa setelah update:");
        while (current != null) {
            System.out.println(no + ". NIM: " + current.id + ", Nama: " + current.name + ", Nilai: " + current.grade);
            current = current.next;
            no++;
        }
        System.out.println();
    }

    // Cek apakah ID sudah dipakai
    public boolean isIdUsed(int id) {
        Node current = head;
        while (current != null) {
            if (current.id == id)
                return true;
            current = current.next;
        }
        return false;
    }

    // Menu CLI
    public static void main(String[] args) {
        StudentList list = new StudentList();
        Scanner scanner = new Scanner(System.in);

        // Data existing (default)
        list.addStudent(12345, "Andi", 85);
        list.addStudent(67890, "Budi", 90);

        int choice;
        do {
            System.out.println("=== MENU MANAJEMEN MAHASISWA ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tambah Mahasiswa");
            System.out.println("3. Update Nilai Mahasiswa");
            System.out.println("4. Hapus Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline

            switch (choice) {
                case 1:
                    list.displayStudents();
                    break;
                case 2:
                    System.out.print("Masukkan NIM Mahasiswa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (list.isIdUsed(id)) {
                        System.out.println("NIM sudah digunakan.");
                        break;
                    }
                    System.out.print("Masukkan Nama: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Nilai: ");
                    int grade = scanner.nextInt();
                    list.addStudent(id, name, grade);
                    break;
                case 3:
                    System.out.print("Masukkan NIM Mahasiswa: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Masukkan Nilai Baru: ");
                    int newGrade = scanner.nextInt();
                    list.updateGrade(updateId, newGrade);
                    list.displayStudentsAfterUpdate();
                    break;
                case 4:
                    System.out.print("Masukkan NIM Mahasiswa yang akan dihapus: ");
                    int deleteId = scanner.nextInt();
                    list.removeStudent(deleteId);
                    break;
                case 5:
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 5);

        scanner.close();
    }
}