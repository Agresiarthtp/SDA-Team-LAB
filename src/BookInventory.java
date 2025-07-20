public class BookInventory {
    private Book head;

    public BookInventory() {
        this.head = null;
    }

    // Menambahkan buku baru ke inventaris
    public void addBook(String id, String title, int stock) {
        // Validasi data
        if (id.isEmpty() || title.isEmpty() || stock < 0) {
            System.out.println("Data buku tidak valid!");
            return;
        }

        // Cek apakah ID buku sudah ada
        if (findBook(id) != null) {
            System.out.println("Buku dengan ID " + id + " sudah ada dalam inventaris!");
            return;
        }

        // Buat node buku baru
        Book newBook = new Book(id, title, stock);

        // Jika list kosong
        if (head == null) {
            head = newBook;
            System.out.println("Buku '" + title + "' (ID: " + id + ") berhasil ditambahkan ke inventaris.");
            return;
        }

        // Tambahkan ke akhir list
        Book current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newBook);

        System.out.println("Buku '" + title + "' (ID: " + id + ") berhasil ditambahkan ke inventaris.");
    }

    // Menghapus buku dari inventaris
    public void removeBook(String id) {
        // Jika list kosong
        if (head == null) {
            System.out.println("Inventaris buku kosong!");
            return;
        }

        // Jika yang dihapus adalah head
        if (head.getId().equals(id)) {
            System.out.println("Buku '" + head.getTitle() + "' (ID: " + id + ") berhasil dihapus dari inventaris.");
            head = head.getNext();
            return;
        }

        // Cari buku yang akan dihapus
        Book current = head;
        while (current.getNext() != null && !current.getNext().getId().equals(id)) {
            current = current.getNext();
        }

        // Jika buku ditemukan
        if (current.getNext() != null) {
            System.out.println("Buku '" + current.getNext().getTitle() + "' (ID: " + id + ") berhasil dihapus dari inventaris.");
            current.setNext(current.getNext().getNext());
        } else {
            System.out.println("Buku dengan ID " + id + " tidak ditemukan dalam inventaris!");
        }
    }

    // Memperbarui stok buku
    public void updateBookStock(String id, int newStock) {
        // Validasi stok
        if (newStock < 0) {
            System.out.println("Jumlah stok tidak valid! Stok harus lebih dari atau sama dengan 0.");
            return;
        }

        // Cari buku
        Book book = findBook(id);

        if (book != null) {
            int oldStock = book.getStock();
            book.setStock(newStock);
            System.out.println("Stok buku '" + book.getTitle() + "' (ID: " + id + ") berhasil diupdate dari " + 
                              oldStock + " menjadi " + newStock);
        } else {
            System.out.println("Buku dengan ID " + id + " tidak ditemukan dalam inventaris!");
        }
    }

    // Menampilkan semua buku dalam inventaris
    public void displayBooks() {
        if (head == null) {
            System.out.println("Inventaris buku kosong!");
            return;
        }

        System.out.println("\nDaftar Buku dalam Inventaris:");
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-35s %-10s%n", "No", "ID", "Judul Buku", "Stok");
        System.out.println("------------------------------------------------------------------");

        Book current = head;
        int count = 1;
        while (current != null) {
            System.out.printf("%-5d %-15s %-35s %-10d%n", 
                            count, current.getId(), current.getTitle(), current.getStock());
            current = current.getNext();
            count++;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Total buku dalam inventaris: " + (count - 1));
    }

    // Mencari buku berdasarkan ID
    public Book findBook(String id) {
        Book current = head;
        while (current != null) {
            if (current.getId().equals(id)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    // Memeriksa ketersediaan buku
    public boolean isBookAvailable(String id) {
        Book book = findBook(id);
        return book != null && book.getStock() > 0;
    }

    // Mengurangi stok buku (untuk peminjaman)
    public boolean decreaseStock(String id) {
        Book book = findBook(id);
        if (book != null && book.getStock() > 0) {
            book.setStock(book.getStock() - 1);
            return true;
        }
        return false;
    }

    // Menambah stok buku (untuk pengembalian)
    public boolean increaseStock(String id) {
        Book book = findBook(id);
        if (book != null) {
            book.setStock(book.getStock() + 1);
            return true;
        }
        return false;
    }

    // Method untuk analisis kinerja Linked List
    public void analyzePerformance() {
        System.out.println("\nAnalisis Kinerja Linked List dalam Inventaris Buku:");
        System.out.println("1. Kelebihan:\n" +
                         "   - Penambahan dan penghapusan buku sangat fleksibel\n" +
                         "   - Tidak memerlukan alokasi memori yang berurutan\n" +
                         "   - Mudah untuk melakukan perubahan dinamis pada inventaris");
        System.out.println("2. Kekurangan:\n" +
                         "   - Pencarian buku memiliki kompleksitas O(n)\n" +
                         "   - Tidak dapat melakukan akses acak seperti array\n" +
                         "   - Membutuhkan memori tambahan untuk menyimpan referensi");
    }
}
