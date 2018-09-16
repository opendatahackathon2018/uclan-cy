package io.github.nearchos.water.data;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable, Comparable<Score> {

    private String nickname;
    private long score;
    private Date date;

    public Score(String nickname, long score, Date date) {
        this.nickname = nickname;
        this.score = score;
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public long getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int compareTo(Score other) {
        if(score == other.score) return date.compareTo(other.date);
        else return Long.compare(score, other.score);
    }
}