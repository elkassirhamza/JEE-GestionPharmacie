package com.exemple.pharmacie.model;

public class Article {
	protected int id;
	protected String name;
	protected float prix;
	protected int quantite;
	
	public Article() {
	}
	
	public Article(String name, float prix, int quantite) {
		super();
		this.name = name;
		this.prix = prix;
		this.quantite = quantite;
	}

	public Article(int id, String name, float prix, int quantite) {
		super();
		this.id = id;
		this.name = name;
		this.prix = prix;
		this.quantite = quantite;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
