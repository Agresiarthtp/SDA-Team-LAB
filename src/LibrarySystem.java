public class LibrarySystem {
    private BookInventory inventory;
    private WaitingQueue waitingQueue;
    private TransactionHistory transactionHistory;

    public LibrarySystem() {
        inventory = new BookInventory();
        waitingQueue = new WaitingQueue();
        transactionHistory = new TransactionHistory();

        // Menambahkan beberapa buku awal ke inventaris
        initializeBooks();
    }

    // Inisialisasi beberapa buku ke dalam inventaris
    private void initializeBooks() {
        inventory.addBook("B001", "Harry Potter dan Batu Bertuah", 3);
        inventory.addBook("B002", "Lord of the Rings: Fellowship of the Ring", 2);
        inventory.addBook("B003", "Laskar Pelangi", 5);
        inventory.addBook("B004", "Clean Code: A Handbook of Agile Software", 1);
        inventory.addBook("B005", "Filosofi Teras", 4);
    }

    // Mengelola antrian peminjam buku
    public void manageBorrowQueue() {
        // Menampilkan semua buku terlebih dahulu
        inventory.displayBooks();
    }

    // Menambahkan anggota ke antrian
    public void addMemberToQueue(String memberId, String name, String bookId) {
        // Validasi keberadaan buku
        Book book = inventory.findBook(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan dalam inventaris!");
            return;
        }

        // Validasi ketersediaan buku
        if (book.getStock() <= 0) {
            System.out.println("Buku '" + book.getTitle() + "' sedang tidak tersedia. Anda akan ditambahkan ke daftar tunggu.");
        }

        // Tambahkan ke antrian
        waitingQueue.enqueue(memberId, name, bookId);
    }

    // Melayani anggota dari antrian
    public void serveMember() {
        Member member = waitingQueue.dequeue();
        if (member != null) {
            String bookId = member.getRequestedBookId();

            // Cek ketersediaan buku
            if (inventory.isBookAvailable(bookId)) {
                // Kurangi stok buku
                inventory.decreaseStock(bookId);

                // Catat transaksi
                transactionHistory.addTransaction(member.getMemberId(), bookId, TransactionType.BORROW);

                Book book = inventory.findBook(bookId);
                System.out.println(member.getName() + " berhasil meminjam buku '" + book.getTitle() + "'. Stok tersisa: " + book.getStock());
            } else {
                System.out.println("Maaf, buku yang diminta oleh " + member.getName() + " tidak tersedia.");
            }
        }
    }

    // Mengembalikan buku
    public void returnBook(String memberId, String bookId) {
        Book book = inventory.findBook(bookId);
        if (book == null) {
            System.out.println("Buku dengan ID " + bookId + " tidak ditemukan dalam inventaris!");
            return;
        }

        // Tambah stok buku
        inventory.increaseStock(bookId);

        // Catat transaksi
        transactionHistory.addTransaction(memberId, bookId, TransactionType.RETURN);

        System.out.println("Buku '" + book.getTitle() + "' berhasil dikembalikan. Stok sekarang: " + book.getStock());
    }

    // Undo transaksi terakhir
    public void undoLastTransaction() {
        Transaction lastTransaction = transactionHistory.undo();
        if (lastTransaction != null) {
            // Balikkan efek transaksi
            if (lastTransaction.getType() == TransactionType.BORROW) {
                // Jika transaksi terakhir adalah peminjaman, kembalikan stok
                inventory.increaseStock(lastTransaction.getBookId());
                Book book = inventory.findBook(lastTransaction.getBookId());
                System.out.println("Stok buku '" + book.getTitle() + "' dikembalikan. Stok sekarang: " + book.getStock());
            } else {
                // Jika transaksi terakhir adalah pengembalian, kurangi stok
                inventory.decreaseStock(lastTransaction.getBookId());
                Book book = inventory.findBook(lastTransaction.getBookId());
                System.out.println("Stok buku '" + book.getTitle() + "' dikurangi. Stok sekarang: " + book.getStock());
            }
        }
    }

    // Redo transaksi yang telah di-undo
    public void redoTransaction() {
        Transaction redoTransaction = transactionHistory.redo();
        if (redoTransaction != null) {
            // Terapkan kembali efek transaksi
            if (redoTransaction.getType() == TransactionType.BORROW) {
                // Jika transaksi adalah peminjaman, kurangi stok
                inventory.decreaseStock(redoTransaction.getBookId());
                Book book = inventory.findBook(redoTransaction.getBookId());
                System.out.println("Stok buku '" + book.getTitle() + "' dikurangi kembali. Stok sekarang: " + book.getStock());
            } else {
                // Jika transaksi adalah pengembalian, tambah stok
                inventory.increaseStock(redoTransaction.getBookId());
                Book book = inventory.findBook(redoTransaction.getBookId());
                System.out.println("Stok buku '" + book.getTitle() + "' ditambah kembali. Stok sekarang: " + book.getStock());
            }
        }
    }

    // Getter untuk semua komponen
    public BookInventory getInventory() {
        return inventory;
    }

    public WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    // Analisis perbandingan kinerja semua struktur data
    public void analyzeAllPerformance() {
        System.out.println("\n===== ANALISIS KINERJA STRUKTUR DATA DALAM SISTEM PERPUSTAKAAN =====");

        // Analisis Queue
        waitingQueue.analyzePerformance();

        // Analisis Stack
        transactionHistory.analyzePerformance();

        // Analisis Linked List
        inventory.analyzePerformance();

        // Perbandingan keseluruhan
        System.out.println("\n===== PERBANDINGAN STRUKTUR DATA DALAM SISTEM PERPUSTAKAAN =====");
        System.out.println("1. Queue (Antrian Peminjaman):\n" +
                         "   - Sangat efisien untuk mengelola daftar tunggu peminjam berdasarkan urutan kedatangan\n" +
                         "   - Memastikan pelayanan yang adil (first come, first served)\n");

        System.out.println("2. Stack (Histori Transaksi):\n" +
                         "   - Ideal untuk melacak transaksi terbaru dan mendukung fitur undo/redo\n" +
                         "   - Memberikan kemampuan untuk membatalkan kesalahan transaksi\n");

        System.out.println("3. Linked List (Inventaris Buku):\n" +
                         "   - Fleksibel untuk mengelola data buku yang dinamis\n" +
                         "   - Mendukung penambahan dan penghapusan buku tanpa batasan ukuran\n");

        System.out.println("\nKESIMPULAN:\n" +
                         "Kombinasi ketiga struktur data ini menciptakan sistem perpustakaan yang efisien " +
                         "dengan kemampuan untuk mengelola inventaris, antrean peminjam, dan histori transaksi. " +
                         "Masing-masing struktur data dipilih berdasarkan karakteristik dan keunggulannya " +
                         "untuk menangani aspek tertentu dari sistem.");
    }
}
