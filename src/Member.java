public class Member {
    private String memberId;
    private String name;
    private String requestedBookId;
    private Member next;

    public Member(String memberId, String name, String requestedBookId) {
        this.memberId = memberId;
        this.name = name;
        this.requestedBookId = requestedBookId;
        this.next = null;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestedBookId() {
        return requestedBookId;
    }

    public void setRequestedBookId(String requestedBookId) {
        this.requestedBookId = requestedBookId;
    }

    public Member getNext() {
        return next;
    }

    public void setNext(Member next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ID: " + memberId + ", Nama: " + name + ", ID Buku: " + requestedBookId;
    }
}
