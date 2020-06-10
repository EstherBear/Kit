//self-define object stored in the ArrayList
package com.example.kit;

public class Sentence {
    private String display;
    private int audioResourceID;

    public Sentence(String display, int audioResourceID) {
        this.display = display;
        this.audioResourceID = audioResourceID;
    }

    public String getDisplay() {
        return display;
    }

    public int getAudioResourceID() {
        return audioResourceID;
    }
}
