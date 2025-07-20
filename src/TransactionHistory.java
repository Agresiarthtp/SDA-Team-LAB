import java.util.Stack;

enum TransactionType {
    BORROW,
    RETURN
}

class Transaction {
    private String memberId;
    private String bookId;
    private TransactionType type;
    private String timestamp;

    public Transaction(String memberId, String bookId, TransactionType type) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.type = type;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + 
               (type == TransactionType.BORROW ? "PINJAM: " : "KEMBALI: ") +
               "Anggota ID: " + memberId + ", Buku ID: " + bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public TransactionType getType() {
        return type;
    }
}

public class TransactionHistory {
    private Stack<Transaction> undoStack;
    private Stack<Transaction> redoStack;

    public TransactionHistory() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    // Menambahkan transaksi baru
    public void addTransaction(String memberId, String bookId, TransactionType type) {
        Transaction newTransaction = new Transaction(memberId, bookId, type);
        undoStack.push(newTransaction);
        redoStack.clear(); // Hapus semua transaksi di redoStack karena ada transaksi baru

        System.out.println("Transaksi berhasil dicatat: " + 
                          (type == TransactionType.BORROW ? "Peminjaman" : "Pengembalian") + 
                          " buku ID " + bookId + " oleh anggota ID " + memberId);
    }

    // Undo transaksi terakhir
    public Transaction undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada transaksi yang dapat dibatalkan!");
            return null;
        }

        Transaction lastTransaction = undoStack.pop();
        redoStack.push(lastTransaction);

        System.out.println("Transaksi terakhir dibatalkan: " + lastTransaction);
        return lastTransaction;
    }

    // Redo transaksi yang telah di-undo
    public Transaction redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Tidak ada transaksi yang dapat dikembalikan!");
            return null;
        }

        Transaction redoTransaction = redoStack.pop();
        undoStack.push(redoTransaction);

        System.out.println("Transaksi dikembalikan: " + redoTransaction);
        return redoTransaction;
    }

    // Menampilkan seluruh histori transaksi
    public void displayTransactionHistory() {
        if (undoStack.isEmpty()) {
            System.out.println("\nBelum ada transaksi yang dicatat!");
            return;
        }

        System.out.println("\nHistori Transaksi (dari yang terbaru):");
        System.out.println("------------------------------------------------------------------");

        // Copy stack untuk menampilkan tanpa mengubah isi stack asli
        Stack<Transaction> tempStack = new Stack<>();
        tempStack.addAll(undoStack);

        int count = 1;
        while (!tempStack.isEmpty()) {
            System.out.println(count + ". " + tempStack.pop());
            count++;
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("Total transaksi: " + (count - 1));
    }

    // Method untuk analisis kinerja Stack
    public void analyzePerformance() {
        System.out.println("\nAnalisis Kinerja Stack dalam Histori Transaksi:");
        System.out.println("1. Kelebihan:\n" +
                         "   - Operasi push dan pop memiliki kompleksitas O(1)\n" +
                         "   - Implementasi LIFO (Last In First Out) cocok untuk undo/redo\n" +
                         "   - Efisien untuk melacak histori transaksi terakhir");
        System.out.println("2. Kekurangan:\n" +
                         "   - Tidak bisa mengakses transaksi di tengah stack\n" +
                         "   - Transaksi lama akan sulit diakses\n" +
                         "   - Memori bisa membesar jika terlalu banyak transaksi disimpan");
    }
}
