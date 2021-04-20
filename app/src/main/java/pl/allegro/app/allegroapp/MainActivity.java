package pl.allegro.app.allegroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.Callable;

import pl.allegro.app.allegroapp.githubapi.GithubService;
import pl.allegro.app.allegroapp.githubapi.Repository;
import pl.allegro.app.allegroapp.githubapi.RepositoryList;
import pl.allegro.app.allegroapp.githubapi.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BasicActivity{

    @Override
    protected Fragment createFragment() {
        return new RepositoryListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}