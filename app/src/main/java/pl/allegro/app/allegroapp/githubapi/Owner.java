package pl.allegro.app.allegroapp.githubapi;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("login")
    String login;
    @SerializedName("id")
    long id;
    @SerializedName("type")
    String type;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return id + ": " + login;
    }
}
