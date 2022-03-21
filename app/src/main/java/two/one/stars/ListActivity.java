package two.one.stars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import two.one.stars.adapter.StarAdapter;
import two.one.stars.beans.Star;
import two.one.stars.service.StarService;

public class ListActivity extends AppCompatActivity {
    private StarService service;
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init() {
        service.create(new Star("Sono Bisque Doll wa Koi wo Suru " ,"https://uploads.mangadex.org/covers/aa6c76f7-5f5f-46b6-a800-911145f81b9b/1b8e44fb-8c40-4f3c-a472-1152fbd22cb9.jpg", 3.5f));
        service.create(new Star(" Attack on Titan ", "https://uploads.mangadex.org/covers/304ceac3-8cdb-4fe7-acf7-2b6ff7a60613/12158456-0511-468b-be37-8d2aa3772723.png.512.jpg", 3));
        service.create(new Star("Sousou no Frieren", "https://www.anime-planet.com/images/manga/covers/sousou-no-frieren-49409.jpg?t=1588532408", 4));
        service.create(new Star("ChainsawMan", "https://uploads.mangadex.org/covers/a77742b1-befd-49a4-bff5-1ad4e6b0ef7b/8c3c169a-85fd-45d4-b697-a3ed9eb00419.jpg.512.jpg", 5));
        service.create(new Star("Jujutsu Kaisen", "https://uploads.mangadex.org/covers/979920eb-5a86-4536-bc9a-8decd889eec1/8e64ffd7-1747-478d-8e61-d5ef9ea1a99c.jpg.512.jpg", 4));
        service.create(new Star("Kimetsu No Yaiba", "https://uploads.mangadex.org/covers/789642f8-ca89-4e4e-8f7b-eee4d17ea08b/8dae1dbe-e288-4a86-bcaf-9704886921b4.jpg.512.jpg", 4));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Stars").setText(txt).startChooser();
        }
        return super.onOptionsItemSelected(item);
    }

}