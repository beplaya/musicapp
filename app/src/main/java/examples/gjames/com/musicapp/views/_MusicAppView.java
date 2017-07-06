package examples.gjames.com.musicapp.views;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;

/**
 * Super class for all views
 */
public abstract class _MusicAppView<T extends _MusicAppController> {

    protected _MusicAppActivity activity;
    private _MusicAppController controller;

    public void init(_MusicAppActivity activity, _MusicAppController controller) {
        this.activity = activity;
        this.controller = controller;
        activity.setContentView(getLayoutId());
        bind(activity, controller);
    }

    public EditText findEditText(int id) {
        return (EditText) findViewById(id);
    }

    public Button findButton(int id) {
        return (Button) findViewById(id);
    }

    public TextView findTextView(int id) {
        return (TextView) findViewById(id);
    }

    public ListView findListView(int id) {
        return (ListView) findViewById(id);
    }

    public ImageView findImageView(int id) {
        return (ImageView) findViewById(id);
    }

    public View findViewById(int id) {
        return activity.findViewById(id);
    }

    protected T getController() {
        return (T) controller;
    }

    protected abstract void bind(_MusicAppActivity activity, _MusicAppController controller);

    protected abstract int getLayoutId();

}
