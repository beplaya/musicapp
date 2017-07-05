package examples.gjames.com.musicapp.controllers;

import examples.gjames.com.musicapp.activities.MusicAppActivity;
import examples.gjames.com.musicapp.views.MusicAppView;

public abstract class MusicAppController {

    private MusicAppView view;

    public void init(MusicAppActivity activity) {
        view = createView();
        if(view == null){
            throw new IllegalStateException(String.format("View is null in this controller! %s", getClass().getSimpleName()));
        } else {
            view.init(activity, this);
        }
    }

    protected abstract MusicAppView createView();
}
