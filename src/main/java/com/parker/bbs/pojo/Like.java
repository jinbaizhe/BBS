package com.parker.bbs.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Like implements Serializable {
    private User user;
    private Post post;
//    score中1表示点赞，-1表示踩
    private int score;
    private Timestamp time;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
