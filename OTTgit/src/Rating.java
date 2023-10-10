public class Rating {
    int Uid;
    int Tid;
    int rating;

    public void setUid(int uid){
        this.Uid = uid;
    }
    public void setTid(int tid){
        this.Tid = tid;
    }
    public void setRating(int rat){
        this.rating = rat;
    }

    public int getUid(){
        return this.Uid;
    }
    public int getTid(){
        return this.Tid;
    }
    public int getRating(){
        return this.rating;
    }
}
