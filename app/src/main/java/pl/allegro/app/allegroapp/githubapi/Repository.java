package pl.allegro.app.allegroapp.githubapi;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("full_name")
    String fullName;
    @SerializedName("private")
    boolean isPrivate;
    @SerializedName("id")
    long id;
    @SerializedName("default_branch")
    String defaultBranch;
    @SerializedName("size")
    int size;
    //dodać owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
