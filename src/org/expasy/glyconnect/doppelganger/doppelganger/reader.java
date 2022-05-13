package org.expasy.glyconnect.doppelganger.doppelganger;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 *
 * Its purpose is to obtain a doppelganger object for each file in a directory.
 */
public class reader {
    public static void main(String[] args) throws Exception {
        List<doppelganger> gangers = new ArrayList<>();

        File directory = new File("/home/federico/Documenti/Thesis/Doppelganger/referenceDB/N-Linked");
        File[] files = directory.listFiles();

        int totalDoiless = 0;

        assert files != null;
        for (File file : files){
            Path doiJson = Path.of(String.valueOf(file));
            doppelganger doppel = new doppelganger(doiJson);
            gangers.add(doppel);

            totalDoiless += doppel.doiless;
            //System.out.println(doppel.getDoi()+"\n___________________________________________________________________");
        }

        doppelganger dpg1 = new doppelganger(Path.of(String.valueOf(files[0])));
        doppelganger dpg2 = new doppelganger(Path.of(String.valueOf(files[0])));

        if ( dpg1.getGETObject().equals(dpg2.getGETObject()) ) System.out.println("Equals!");
        /*
        for (int i = 0; i < 10; i++) {
            if ( gangers.get(i).getGETObject().equals(gangers.get(i).getGETObject()) )
                System.out.println("Equals!");
        }

         */

    }
}
