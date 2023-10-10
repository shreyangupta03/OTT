
public interface MediaManagementSystem {//our interface and we will extend them
    public void add_MediaItem(Media m);
    public void update_MediaItem(int ID);
    public void delete_MediaItem(int ID);
    public void change_DefaultLanguage(int ID);
    public void searchMediaByTitle(int id);
    public void filterMediaByLanguage(String language);
    public void addRating(Rating rat);
    public void addPreferredGenres(Genre g);
}