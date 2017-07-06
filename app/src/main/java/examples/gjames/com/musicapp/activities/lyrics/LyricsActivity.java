package examples.gjames.com.musicapp.activities.lyrics;


import examples.gjames.com.musicapp.activities._MusicAppActivity;
import examples.gjames.com.musicapp.controllers.lyrics.LyricsController;

public class LyricsActivity extends _MusicAppActivity<LyricsController> {


    @Override
    protected void onResume() {
        super.onResume();
        controller.onResume();
    }
}
