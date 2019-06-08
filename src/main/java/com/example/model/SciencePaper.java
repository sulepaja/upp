package com.example.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SciencePaper {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(View.FileInfo.class)
	private Long id;	
	
	@Column(nullable = false)
	@JsonView(View.FileInfo.class)
	private String name;

	@ManyToOne()
	@JoinColumn(name = "autor_id")
	private User author;

	@ManyToOne()
	@JoinColumn(name = "editor_id")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private User editor;//urednik


	@Lob
	@Transient
	@Column(name="pic")
	private byte[] pic;
	

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "coAuthor_sciencePaper",
			joinColumns = { @JoinColumn(name = "coAuthor_id") },
			inverseJoinColumns = { @JoinColumn(name = "sciencePaper_id") }
	)
	private List<User> coAuthor;

	@Column(name="co_authors")
	private String co_authors;

	@Column(nullable = false)
	private Boolean approved;

	@Column(nullable = false)
	private String keywords;
	
	@Column(nullable = false)
	private String abbstract;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
	private ScientificField scientificField;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    private Magazine scienceMagazine;

	@Transient
	@Lob
	private MultipartFile[] textPDF;

	@Column(nullable = false)
	private String locationOnDrive;

	@Column(nullable = false)
	private String status;

	//@OneToMany(mappedBy = "sciencePaper", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // BACA ERROR PRI STARTUP
	//@JsonIgnore
	@OneToMany(mappedBy = "sciencePaper", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();

	@Transient
	@ManyToMany(fetch =FetchType.EAGER, mappedBy = "listSciencePaperForReviwer")
	private List<User> reviwers = new ArrayList<>();

	private String nameMagazine;

	private String nameScientifiField;

	public SciencePaper(){}
	
	public SciencePaper(String name, String keywords, String abbstract, ScientificField scientificField, MultipartFile[] textPDF) {
		super();
		this.name = name;
		this.keywords = keywords;
		this.abbstract = abbstract;
		this.scientificField = scientificField;
		this.textPDF = textPDF;
		this.approved = false;
		this.status = "editor";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getCo_authors() {
		return co_authors;
	}

	public void setCo_authors(String co_authors) {
		this.co_authors = co_authors;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getLocationOnDrive() {
		return locationOnDrive;
	}

	public void setLocationOnDrive(String locationOnDrive) {
		this.locationOnDrive = locationOnDrive;
	}

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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getAbbstract() {
		return abbstract;
	}

	public void setAbbstract(String abbstract) {
		this.abbstract = abbstract;
	}

	public ScientificField getScentificField() {
		return scientificField;
	}

	public void setScentificField(ScientificField scientificField) {
		this.scientificField = scientificField;
	}

	public MultipartFile[] getTextPDF() {
		return textPDF;
	}

	public void setTextPDF(MultipartFile[] textPDF) {
		this.textPDF = textPDF;
	}

	public String getNameMagazine() {
		return nameMagazine;
	}

	public void setNameMagazine(String nameMagazine) {
		this.nameMagazine = nameMagazine;
	}

	public String getNameScientifiField() {
		return nameScientifiField;
	}

	public void setNameScientifiField(String nameScientifiField) {
		this.nameScientifiField = nameScientifiField;
	}

	public ScientificField getScientificField() {
		return scientificField;
	}

	public void setScientificField(ScientificField scientificField) {
		this.scientificField = scientificField;
	}

	public Magazine getScienceMagazine() {
		return scienceMagazine;
	}

	public void setScienceMagazine(Magazine scienceMagazine) {
		this.scienceMagazine = scienceMagazine;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<User> getCoAuthor() {
		return coAuthor;
	}

	public void setCoAuthor(List<User> coAuthor) {
		this.coAuthor = coAuthor;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

}
