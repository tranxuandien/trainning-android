package com.example.gl.study1;

/**
 * Created by truongdat277 on 8/22/2016.
 */
public class Project {
    private String id;
    private String name;
    private int progress;

    public Project(String id, String name, int progress) {
        this.id = id;
        this.name = name;
        this.progress = progress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
