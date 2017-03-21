package com.colaui.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Carl on 2017/3/15.
 */
@Entity
@Table(name = "DEPT")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Dept implements Serializable{
    private int id;
    private String name;
    private Timestamp date;
    private String description;
    private Integer companyId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private Company company;
    private Collection<Employee> employees;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name = "companyId")
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", updatable = false, insertable = false)
    @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptId", updatable = false, insertable = false)
    @JsonIgnore
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
