package examples.gjames.com.musicapp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import examples.gjames.com.musicapp.R;

public class MusicAppActivity extends AppCompatActivity {

    protected MusicAppController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_start);
    }

    private void init() {
        controller = createController();
        if(controller == null){
            throw new IllegalStateException(String.format("Controller is null in this activity! %s", getClass().getSimpleName()));
        } else {
            controller.init(this);
        }
    }

    protected MusicAppController createController() {
        return controller;
    }
}
