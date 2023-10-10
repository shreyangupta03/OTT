public class Genre {
    int Uid;
    String prefGenre1;
    String prefGenre2;
    String prefGenre3;

    public void setUid(int uid){
        this.Uid = uid;
    }
    public void setGenre1(String gen){
        this.prefGenre1 = gen;
    }
    public void setGenre2(String gen){
        this.prefGenre2 = gen;
    }
    public void setGenre3(String gen){
        this.prefGenre3 = gen;
    }

    public int getUid(){
        return this.Uid;
    }
    public String getGenre1(){
        return this.prefGenre1;
    }
    public String getGenre2(){
        return this.prefGenre2;
    }
    public String getGenre3(){
        return this.prefGenre3;
    }
}
