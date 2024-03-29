package com.example.examApp.data.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
/**
 * @author Chen.
 * @version 1 at 30/5/2019.
 *DatabaseHero the object stored in memory
 */
@Entity(tableName = "HeroList")
public class DatabaseHero {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mTitle;
    private String mAbilities;
    private String mImageUrl;
    private boolean mFavorite;

    public DatabaseHero(int id, String mTitle, String mAbilities, String mImageUrl, boolean mFavorite) {
        setId(id);
        setTitle(mTitle);
        setAbilities(mAbilities);
        setImageUrl(mImageUrl);
        setFavorite(mFavorite);
    }

    @Ignore
    public DatabaseHero(String mTitle, String mAbilities, String mImageUrl, boolean mFavorite) {
        setTitle(mTitle);
        setAbilities(mAbilities);
        setImageUrl(mImageUrl);
        setFavorite(mFavorite);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return mTitle;
    }

    private void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getAbilities() {
        return mAbilities;
    }

    private void setAbilities(String mAbilities) {
        this.mAbilities = mAbilities;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    private void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public boolean getFavorite() {
        return mFavorite;
    }

    public void setFavorite(boolean mFavorite) {

        this.mFavorite = mFavorite;
    }

}
