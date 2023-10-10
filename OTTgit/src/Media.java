public class Media {
    int Tid;
    String title;
    String releaseDate;

    public void setTid(int tid) {
        this.Tid = tid;
    }
    public void setTitle(String ttl) {
        this.title = ttl;
    }
    public void setReleaseDate(String rd) {
        this.releaseDate = rd;
    }

    public int getTid() {
        return this.Tid;
    }
    public String getTitle() {
        return this.title;
    }
    public String getreleaseDate() {
        return this.releaseDate;
    }
}
