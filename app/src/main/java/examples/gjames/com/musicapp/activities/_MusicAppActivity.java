package examples.gjames.com.musicapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import examples.gjames.com.musicapp.controllers._MusicAppController;

/**
 * Super class for all activities
 * @param <T>
 */
public class _MusicAppActivity<T extends _MusicAppController> extends AppCompatActivity {

    protected Class<T> controllerClass;
    protected _MusicAppController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            init();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void init() throws InstantiationException, IllegalAccessException {
        controller = createController();
        if (controllerClass == null) {
            throw new IllegalStateException(String.format("Controller is null in this activity! %s", getClass().getSimpleName()));
        } else {
            controller.init(this);
        }
    }

    private T createController() throws IllegalAccessException, InstantiationException {
        return controllerClass.newInstance();
    }
}
