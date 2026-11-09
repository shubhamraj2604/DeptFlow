package com.example.DeptFlow.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity 
public class Department {
    @JsonProperty ("id")
    @Id 
    private long id;
    // @JsonProperty ("code")
    private String code;
    @JsonProperty ("name")
    private String name;
    @JsonProperty ("description")
    private String description;
    @JsonProperty ("head_name")
    private String head_name;
    @JsonProperty ("location")
    private String location;
    @JsonProperty ("contact_email")
    private String contact_email;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
