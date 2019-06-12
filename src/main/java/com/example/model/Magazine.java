package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(scope = Magazine.class, property = "id", generator = ObjectIdGenerators.None.class)
public class Magazine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String issn;
	
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "scienceMagazine", cascade = CascadeType.ALL)
	private List<SciencePaper> scientificPapers = new ArrayList<SciencePaper>();

	@ManyToOne()
	@JoinColumn(name = "editor_id")
	private User editor;//urednik


	/*@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
		})
	@JoinTable(name = "magazine_fields",
		joinColumns = @JoinColumn(name = "mag_id"),
		inverseJoinColumns = @JoinColumn(name = "field_id")
			)
	private List<ScientificField> scientificFields = new ArrayList<ScientificField>();
*/
	@ManyToOne
	@JoinColumn(name="scientificFields_id", nullable=false)
	private ScientificField scientificField;
	
	@Column()
	private double price;
	
	@ManyToMany(fetch =FetchType.EAGER, mappedBy = "allowedMagazines")
    private List<User> allowedUsers = new ArrayList<>();

	@OneToMany(mappedBy = "reviewerMagazine", cascade = CascadeType.ALL)
	private List<User> reviewers = new ArrayList<User>();

	public Magazine(String issn, String name, double price) {
		super();
		this.issn = issn;
		this.name = name;		
		this.price = price;
	}

	public Magazine(){}

	public List<User> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<User> reviewers) {
		this.reviewers = reviewers;
	}

	public List<User> getAllowedUsers() {
		return allowedUsers;
	}

	public void setAllowedUsers(List<User> allowedUsers) {
		this.allowedUsers = allowedUsers;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SciencePaper> getScientificPapers() {
		return scientificPapers;
	}

	public void setScientificPapers(List<SciencePaper> scientificPapers) {
		this.scientificPapers = scientificPapers;
	}
/*
	public List<ScientificField> getScientificFields() {
		return scientificFields;
	}

	public void setScientificFields(List<ScientificField> scientificFields) {
		this.scientificFields = scientificFields;
	}
	*/

	public ScientificField getScientificField() {
		return scientificField;
	}

	public void setScientificField(ScientificField scientificField) {
		this.scientificField = scientificField;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}
}
