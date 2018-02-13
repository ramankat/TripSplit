package com.vendoor.apps.tripsplit;

/**
 * Created by dell on 2/13/2018.
 */

public class Person {
    String name,invest;
    Integer pending;

    public Person(String name, String invest, Integer pending) {
        this.name = name;
        this.invest = invest;
        this.pending = pending;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvest() {
        return invest;
    }

    public void setInvest(String invest) {
        this.invest = invest;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }
}
