package com.example.bobpoole.werewolfclient.GameList;

import java.util.List;

/**
 * Created by Bob.Poole on 28/11/2016.
 */
public class GameList {
    private List<String> active;
    private List<String> pending;

    public List<String> getActive() {
        return active;
    }

    public void setActive(List<String> active) {
        this.active = active;
    }

    public List<String> getPending() {
        return pending;
    }

    public void setPending(List<String> pending) {
        this.pending = pending;
    }
}
