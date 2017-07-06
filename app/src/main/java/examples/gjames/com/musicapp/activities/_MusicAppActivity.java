package examples.gjames.com.musicapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;

import examples.gjames.com.musicapp.controllers._MusicAppController;

/**
 * Super class for all activities
 *
 * @param <T>
 */
public class _MusicAppActivity<T extends _MusicAppController> extends AppCompatActivity {

    protected _MusicAppController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Controller or view incorrectly assigned generic.  Controllers and views should be public and have a 0 parameter constructor." +
                    "Did you foget to override 'getControllerClass'?");
        }
    }

    private void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        controller = createController();
        if (controller == null) {
            throw new IllegalStateException(String.format("Controller is null in this activity! %s", getClass().getSimpleName()));
        } else {
            controller.init(this);
        }
    }

    private T createController() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ParameterizedType pt
                = (ParameterizedType) getClass().getGenericSuperclass();
        String parameterClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1];
        return (T) Class.forName(parameterClassName).newInstance();
    }
}
