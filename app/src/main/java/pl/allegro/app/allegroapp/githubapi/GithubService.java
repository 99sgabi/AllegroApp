package pl.allegro.app.allegroapp.githubapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("orgs/allegro/repos")
    public Call<List<Repository>> getRepositories();

    @GET("/repos/allegro/{repo}")
    public Call<Repository> getRepository(@Path("repo") String repositoryName);
}
