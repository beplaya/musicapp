package examples.gjames.com.musicapp.controllers;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.views._MusicAppView;

/**
 * Super class for all controllers
 */
public abstract class _MusicAppController {

    private _MusicAppView view;
    protected _MusicAppActivity activity;

    public void init(_MusicAppActivity activity) {
        this.activity = activity;
        view = createView();
        if(view == null){
            throw new IllegalStateException(String.format("View is null in this controllerClass! %s", getClass().getSimpleName()));
        } else {
            view.init(activity, this);
        }
    }

    protected abstract _MusicAppView createView();
}
