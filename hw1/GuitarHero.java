import es.datastructur.synthesizer.GuitarString;

import javax.print.attribute.standard.MediaTray;

public class GuitarHero
{
    public static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */

        GuitarString[] notes = new GuitarString[37];
        for(int i = 0; i < notes.length; i++)
        {
            double frequency = 440 * (Math.pow(2, (double)(i - 24) / 12));
            notes[i] = new GuitarString(frequency);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            int index = -1;
            GuitarString currNote = null;
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                index = keyboard.indexOf(key);
                if(index >= 0)
                {
                    currNote = notes[index];
                    currNote.pluck();
                }
            }

            double sample = 0;
            /* compute the superposition of samples */
            for(int i = 0; i < notes.length; i++)
            {
                double currSample = notes[i].sample();
                if(currSample != 0) {
                    sample += notes[i].sample();
                    /* advance the simulation of each guitar string by one step */
                    notes[i].tic();
                }
            }
            /* play the sample on standard audio */
            if(sample != 0) {
                System.out.println(sample);
            }
            StdAudio.play(sample);

        }
    }
}
