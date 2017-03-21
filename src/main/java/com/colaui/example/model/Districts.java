package com.colaui.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Carl on 2017/3/15.
 */
@Entity
@Table(name = "DISTRICTS")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Districts implements Serializable{
    private int id;
    private Integer level;
    private String name;
    private String shortName;
    private String enName;
    private String cityCode;
    private String postCode;
    private Double lng;
    private Double lat;
    private Byte dcm;
    private Integer sortFactor;
    private Integer parentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name = "shortName")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    
    @Column(name = "enName")
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    
    @Column(name = "cityCode")
    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    
    @Column(name = "postCode")
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    
    @Column(name = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    
    @Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    
    @Column(name = "dcm")
    public Byte getDcm() {
        return dcm;
    }

    public void setDcm(Byte dcm) {
        this.dcm = dcm;
    }

    
    @Column(name = "sortFactor")
    public Integer getSortFactor() {
        return sortFactor;
    }

    public void setSortFactor(Integer sortFactor) {
        this.sortFactor = sortFactor;
    }

    
    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    
    @Column(name = "createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    
    @Column(name = "updatedAt")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
