package pl.allegro.app.allegroapp.githubapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("orgs/allegro/repos")
    public Call<RepositoryList> getRepositories();

    @GET("/repos/allegro/{repo}")
    public Call<Repository> getRepository(@Path("repo") String repositoryName);
}
