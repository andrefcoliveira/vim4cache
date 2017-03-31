package org.academiadecodigo.vim4cache.user;

/**
 * Created by codecadet on 31/03/17.
 */
public class User {

    private int id;
    private String name;
    private int score;

    public User(String id, int name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
