package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="paper_download")
public class SciencePaperDownload {
	
	@Id
	@GeneratedValue
    @Column(name = "id")
	@JsonView(View.FileInfo.class)
    private Long id;
	
	@Column(name = "name")
	@JsonView(View.FileInfo.class)
	private String name;
	    
	@Column(name = "mimetype")
	private String mimetype;
	
	@Lob
    @Column(name="pic")
    private byte[] pic;

	public SciencePaperDownload(String name, String mimetype, byte[] pic) {
		super();
		this.name = name;
		this.mimetype = mimetype;
		this.pic = pic;
	}
	
	public SciencePaperDownload(){}

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

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	
	

}
