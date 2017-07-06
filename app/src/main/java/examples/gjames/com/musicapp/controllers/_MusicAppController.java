package examples.gjames.com.musicapp.controllers;

import java.lang.reflect.ParameterizedType;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.views._MusicAppView;

/**
 * Super class for all controllers
 */
public abstract class _MusicAppController<T extends _MusicAppView> {

    private _MusicAppView view;
    protected _MusicAppActivity activity;

    public void init(_MusicAppActivity activity) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.activity = activity;
        view = createView();
        if (view == null) {
            throw new IllegalStateException(String.format("View is null in this controllerClass! %s", getClass().getSimpleName()));
        } else {
            view.init(activity, this);
        }
    }

    private T createView() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ParameterizedType pt
                = (ParameterizedType) getClass().getGenericSuperclass();
        String parameterClassName = pt.getActualTypeArguments()[0].toString().split("\\s")[1];
        return (T) Class.forName(parameterClassName).newInstance();
    }

    protected T getView() {
        return (T) view;
    }

    public abstract void onResume();
}
