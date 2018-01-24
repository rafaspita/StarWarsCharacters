package com.rspitaliere.starwarscharacters.adapters.characters;

/**
 * Created by rspitaliere on 31/08/17.
 */

public class CharacterClassAdapter {
    private String character, user, link;

    public CharacterClassAdapter(String character, String user, String link){
        this.character = character;
        this.user = user;
        this.link = link;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
