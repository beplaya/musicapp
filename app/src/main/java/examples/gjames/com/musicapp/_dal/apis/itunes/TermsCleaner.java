package examples.gjames.com.musicapp._dal.apis.itunes;

import java.util.ArrayList;
import java.util.List;

public class TermsCleaner {

    public String clean(String string) {
        string = string.trim();
        string = string.replaceAll("[^A-Za-z0-9]", "");
        return string;
    }

    public String[] cleanTerms(String[] terms) {
        List<String> cleaned = new ArrayList<>();
        for (String term : terms) {
            String[] split = term.split("\\s+");
            for (String s : split) {
                s = clean(s);
                if (!s.isEmpty()) {
                    cleaned.add(s);
                }
            }
        }
        return (String[]) cleaned.toArray();
    }
}
