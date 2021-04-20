package pl.allegro.app.allegroapp.githubapi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String GITHUB_API = "https://api.github.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit()
    {
        if(retrofit == null)
        {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                                        .addInterceptor(interceptor)
                                        .build();
            retrofit = new Retrofit.Builder()
                                    .baseUrl(GITHUB_API)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .client(client)
                                    .build();
        }
        return retrofit;
    }
}
