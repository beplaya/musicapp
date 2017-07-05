package examples.gjames.com.musicapp.views;

import examples.gjames.com.musicapp.activities.MusicAppActivity;
import examples.gjames.com.musicapp.controllers.MusicAppController;

public  abstract class MusicAppView {

    public abstract void init(MusicAppActivity activity, MusicAppController controller);
}
