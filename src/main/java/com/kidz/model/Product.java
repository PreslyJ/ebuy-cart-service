package com.kidz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Product implements Serializable{

    private static final long serialVersionUID = -6989243970039135205L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @NotEmpty(message = "The name must not be null")
    @Length(max = 255, message = "The field must be less than 255 characters")
    private String name;
    
    @Lob
    private String description;
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn
    @JsonIgnoreProperties("products")
    private SubCategory subCategory;
    
    @OneToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER,mappedBy="product")
    @JsonIgnoreProperties("product")
    private List<Item> items=new ArrayList<Item>();

   	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
