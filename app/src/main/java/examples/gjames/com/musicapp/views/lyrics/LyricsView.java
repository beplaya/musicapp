package examples.gjames.com.musicapp.views.lyrics;


import examples.gjames.com.musicapp.R;
import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers._MusicAppController;
import examples.gjames.com.musicapp.controllers.lyrics.LyricsController;
import examples.gjames.com.musicapp.views._MusicAppView;

import static examples.gjames.com.musicapp.R.*;

public class LyricsView extends _MusicAppView<LyricsController> {
    @Override
    protected void bind(_MusicAppActivity activity, _MusicAppController controller) {

    }

    @Override
    protected int getLayoutId() {
        return layout.activity_lyrics;
    }
}
