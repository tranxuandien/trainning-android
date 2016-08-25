package com.example.gl.study1;

import java.io.Serializable;
import java.util.List;

/**
 * Created by truongdat277 on 8/22/2016.
 */
public class Project implements Serializable {
//    @serializedName('id')
    private int id;
//    @serializedName('id')
    private String u_id;
    private String project_name;
    private int construction_category_id;
    private int progress;
    private String post_code;
    private String province;
    private String district;
    private String other;

    public Project(int id, String project_name,int progress) {
        this.id = id;
        this.project_name = project_name;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getConstruction_category_id() {
        return construction_category_id;
    }

    public void setConstruction_category_id(int construction_category_id) {
        this.construction_category_id = construction_category_id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
