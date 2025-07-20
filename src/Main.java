import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QueueManager queue = new QueueManager();

        System.out.println("--Aplikasi Antrian Restoran--");

        while(true){
            System.out.println("\nMenu:");
            System.out.println("1. Tambahkan Antrian Pelanggan:");
            System.out.println("2. Layani Antrian");
            System.out.println("3. Lihat Antrian");
            System.out.println("4. Keluar");
            System.out.println("Pilih opsi: ");

            int pilihan = -1;
            try {
                pilihan =scanner.nextInt();
            }catch(Exception e){
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.next(); // Membersihkan buffer scanner
                continue;
            }
            scanner.nextLine(); // Membersihkan newline character dari buffer

            switch(pilihan){
                case 1:
                    System.out.println("Masukkan nama pelanggan: ");
                    String data = scanner.nextLine();
                    queue.enqueue(data);
                    queue.display();
                    break;

                case 2:
                    queue.dequeque();
                    System.out.println("Antrian telah dilayani\n");
                    queue.display();
                    break;

                case 3:
                    queue.display();
                    break;

                case 4:
                    System.out.println("Keluar dari sistem");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilih tidak valid, silahkan pilih angka 1-4. ");
            }
        }

    }
}