package com.kidz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Category implements Serializable {

    private static final long serialVersionUID = -992248230930241426L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "The name must not be null")
    @Length(max = 255, message = "The field must be less than 255 characters")
    @Column(unique=true)
    private String name;
    
    @Lob
    private String description;
    
    @OneToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER,mappedBy="category")
	@JsonIgnoreProperties("category")
    private List<SubCategory> subCategories=new ArrayList<SubCategory>();

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
