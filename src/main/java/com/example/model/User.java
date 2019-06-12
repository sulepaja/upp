package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String username;	
    
	@Column(nullable = false)
	private String firstname;
    
	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false)
    private String city;    
    
	@Column(nullable = false)
	private String country;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;

	@OneToMany(mappedBy = "scienceMagazine", cascade = CascadeType.ALL)
	private List<SciencePaper> scientificPapers = new ArrayList<SciencePaper>();

	@OneToMany(mappedBy = "editor", cascade = CascadeType.ALL)
	private List<SciencePaper> ListEditor = new ArrayList<SciencePaper>();

//	@ManyToMany(cascade = { CascadeType.ALL })
//	@JoinTable(
//			name = "user_scientificFieldList",
//			joinColumns = { @JoinColumn(name = "editor_id") },
//			inverseJoinColumns = { @JoinColumn(name = "scientificFieldList_id") }
//	)
//	private List<ScientificField> scientificFieldList = new ArrayList<ScientificField>();

    @ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
		}, fetch =FetchType.EAGER)
    @JoinTable(name = "user_magazine",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "mag_id")
		)

    @JsonIgnore
    private List<Magazine> allowedMagazines = new ArrayList<Magazine>();

    @Transient
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Comment> comments = new ArrayList<Comment>();

	@Transient
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE
	}, fetch =FetchType.EAGER)
	@JoinTable(name = "user_sciencePaper",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "siciencePaper_id")
	)
	@JsonIgnore
	private List<SciencePaper> listSciencePaperForReviwer = new ArrayList<SciencePaper>();

	@JsonIgnore
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE,
	})
	@JoinTable(name = "user_scientificField",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "scientificField_id")
	)
	private List<ScientificField> listScientificField= new ArrayList<ScientificField>();

	@ManyToOne
	@JsonIgnore
	private Magazine reviewerMagazine;

	public User(){}

	public User(String username, String firstname, String lastname, String city, String country, String email, String password, String role) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.country = country;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Magazine getReviewerMagazine() {
		return reviewerMagazine;
	}

	public void setReviewerMagazine(Magazine reviewerMagazine) {
		this.reviewerMagazine = reviewerMagazine;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Magazine> getAllowedMagazines() {
		return allowedMagazines;
	}

	public void setAllowedMagazines(List<Magazine> allowedMagazines) {
		this.allowedMagazines = allowedMagazines;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String state) {
		this.country = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ScientificField> getListScientificField() {
		return listScientificField;
	}

	public void setListScientificField(List<ScientificField> listScientificField) {
		this.listScientificField = listScientificField;
	}
}
