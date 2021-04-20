package pl.allegro.app.allegroapp.githubapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryList {
    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public int size()
    {
        return repositories == null ? 0 : repositories.size();
    }
    @SerializedName("")
    List<Repository> repositories = null;
}
