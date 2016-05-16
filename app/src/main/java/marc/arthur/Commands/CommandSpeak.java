package marc.arthur.Commands;

/**
 * Created by marc on 16/05/16.
 */
public class CommandSpeak extends Command {


    String speech;

    public String getSpeech() {
        return speech;
    }

    public CommandSpeak setSpeech(String speech) {
        this.speech = speech;
        return this;
    }
}

