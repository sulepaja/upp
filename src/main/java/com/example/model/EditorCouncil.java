package com.example.model;

public class EditorCouncil {
	private Long id;
	private User mainEditor;
	
	public EditorCouncil() {
		
	}
	
	public EditorCouncil(Long id, User mainEditor) {
		super();
		this.id = id;
		this.mainEditor = mainEditor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getMainEditor() {
		return mainEditor;
	}

	public void setMainEditor(User mainEditor) {
		this.mainEditor = mainEditor;
	}
	
	
}
