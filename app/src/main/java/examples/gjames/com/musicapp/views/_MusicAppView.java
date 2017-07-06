package examples.gjames.com.musicapp.views;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;

/**
 * Super class for all views
 */
public  abstract class _MusicAppView {

    private _MusicAppActivity activity;
    protected _MusicAppController controller;

    public void init(_MusicAppActivity activity, _MusicAppController controller){
        this.activity = activity;
        this.controller = controller;
        activity.setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();
}
