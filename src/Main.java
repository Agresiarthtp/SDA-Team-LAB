import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem librarySystem = new LibrarySystem();

        System.out.println("-- SISTEM MANAJEMEN PERPUSTAKAAN --");

        while(true){
            System.out.println("\nMenu Utama Sistem Perpustakaan:");
            System.out.println("1. Manajemen Buku (Linked List)");
            System.out.println("2. Manajemen Antrian Peminjaman (Queue)");
            System.out.println("3. Transaksi Pinjam-Kembali & Undo/Redo (Stack)");
            System.out.println("4. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.next(); // Membersihkan buffer scanner
                continue;
            }
            scanner.nextLine(); // Membersihkan newline character dari buffer

            switch(pilihan) {
                case 1: // Manajemen Buku (Linked List)
                    manageBookInventory(scanner, librarySystem.getInventory());
                    break;
                case 2: // Manajemen Antrian Peminjaman (Queue)
                    manageWaitingQueue(scanner, librarySystem);
                    break;
                case 3: // Transaksi Pinjam-Kembali & Undo/Redo (Stack)
                    manageTransactions(scanner, librarySystem);
                    break;
                case 4: // Keluar
                    System.out.println("Terima kasih telah menggunakan Sistem Perpustakaan!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silahkan pilih angka 1-5.");
            }
        }
    }

            private static void manageBookInventory(Scanner scanner, BookInventory inventory) {
        while(true) {
            System.out.println("\nMenu Manajemen Buku (Linked List):");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Perbarui Stok Buku");
            System.out.println("4. Tampilkan Semua Buku");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch(pilihan) {
                case 1: // Tambah Buku
                    System.out.print("Masukkan ID buku: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan judul buku: ");
                    String title = scanner.nextLine();
                    System.out.print("Masukkan jumlah stok: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    inventory.addBook(id, title, stock);
                    break;

                case 2: // Hapus Buku
                    System.out.print("Masukkan ID buku yang akan dihapus: ");
                    id = scanner.nextLine();
                    inventory.removeBook(id);
                    break;

                case 3: // Update Stok
                    System.out.print("Masukkan ID buku yang akan diupdate: ");
                    id = scanner.nextLine();
                    System.out.print("Masukkan jumlah stok baru: ");
                    stock = scanner.nextInt();
                    scanner.nextLine(); // clear buffer

                    inventory.updateBookStock(id, stock);
                    break;

                case 4: // Tampilkan Buku
                    inventory.displayBooks();
                    break;

                case 5: // Kembali
                    return;

                default:
                    System.out.println("Pilihan tidak valid, silahkan pilih angka 1-5.");
            }
        }
            }

            private static void manageWaitingQueue(Scanner scanner, LibrarySystem librarySystem) {
        while(true) {
            System.out.println("\nMenu Manajemen Antrian Peminjaman (Queue):");
            System.out.println("1. Tampilkan Daftar Buku");
            System.out.println("2. Tambahkan Anggota ke Daftar Tunggu");
            System.out.println("3. Layani Anggota");
            System.out.println("4. Lihat Daftar Tunggu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch(pilihan) {
                case 1: // Tampilkan Buku
                    librarySystem.getInventory().displayBooks();
                    break;

                case 2: // Tambah Anggota ke Antrian
                    System.out.print("Masukkan ID anggota: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Masukkan nama anggota: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan ID buku yang ingin dipinjam: ");
                    String bookId = scanner.nextLine();

                    librarySystem.addMemberToQueue(memberId, name, bookId);
                    break;

                case 3: // Layani Anggota
                    librarySystem.serveMember();
                    break;

                case 4: // Lihat Antrian
                    librarySystem.getWaitingQueue().display();
                    break;

                case 5: // Kembali
                    return;

                default:
                    System.out.println("Pilihan tidak valid, silahkan pilih angka 1-5.");
            }
        }
    }

            private static void manageTransactions(Scanner scanner, LibrarySystem librarySystem) {
        while(true) {
            System.out.println("\nMenu Transaksi Pinjam-Kembali (Stack):");
            System.out.println("1. Kembalikan Buku");
            System.out.println("2. Undo Transaksi Terakhir");
            System.out.println("3. Redo Transaksi");
            System.out.println("4. Tampilkan Histori Transaksi");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan = scanner.nextInt();
            } catch(Exception e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.next();
                continue;
            }
            scanner.nextLine();

            switch(pilihan) {
                case 1: // Kembalikan Buku
                    System.out.print("Masukkan ID anggota: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Masukkan ID buku yang dikembalikan: ");
                    String bookId = scanner.nextLine();

                    librarySystem.returnBook(memberId, bookId);
                    break;

                case 2: // Undo Transaksi
                    librarySystem.undoLastTransaction();
                    break;

                case 3: // Redo Transaksi
                    librarySystem.redoTransaction();
                    break;

                case 4: // Tampilkan Histori
                    librarySystem.getTransactionHistory().displayTransactionHistory();
                    break;

                case 5: // Kembali
                    return;

                default:
                    System.out.println("Pilihan tidak valid, silahkan pilih angka 1-5.");
            }
        }
    }
}