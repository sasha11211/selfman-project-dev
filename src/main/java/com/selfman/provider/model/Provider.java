package com.selfman.provider.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "_selfman_provider")
public class Provider {
	@Id
	String email;
	String name;
	String password;
	String country;
	String logo;
	Set<String> languages;
	Set<String> industry;
	Set<String> keywords;
	Set<String> products;
	Integer founded;
	Double rating;
	Integer reviews;
	Set<ContactInfo> contactInfo;
	Set<SocialMedia> socialMedia;
	Set<String> roles;

	public Provider() {
		roles = new HashSet<String>();
		rating = 0.0;
		reviews = 0;
	}

	public Provider(String email, String name, String password, String country) {
		this();
		this.email = email;
		this.name = name;
		this.password = password;
		this.country = country;
	}

	public void addRole(String role) {
		roles.add(role);
	}

	public void addIndustry(Set<String> ind) {
		industry.addAll(ind);
	}

	public void removeIndustry(String ind) {
		industry.remove(ind);
	}

	public void addKeyWord(Set<String> key) {
		keywords.addAll(key);
	}

	public void removeKeyWord(String key) {
		keywords.remove(key);
	}

	public void addProduct(Set<String> product) {
		products.addAll(product);
	}

	public void removeProduct(String product) {
		products.remove(product);
	}

	public void addContactInfo(ContactInfo info) {
		contactInfo.add(info);
	}

	public void removeContactInfo(ContactInfo info) {
		contactInfo.remove(info);
	}

	public void addSocialMedia(SocialMedia media) {
		socialMedia.add(media);
	}

	public void removeSocialMedia(SocialMedia media) {
		socialMedia.remove(media);
	}

}