package two.one.stars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class SplashActivity extends AppCompatActivity {
    VideoView logo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(4500 );
                    Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        setContentView(R.layout.activity_splash_ativity);
        logo = findViewById(R.id.videoView);

//        logo.animate().rotation(360f).setDuration(2000);
//        logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
//        logo.animate().translationYBy(1000f).setDuration(2000);
//        logo.animate().alpha(0f).setDuration(6000);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.tone);
        logo.setVideoURI(uri);
        logo.start();
        t.start();
    }

;

}