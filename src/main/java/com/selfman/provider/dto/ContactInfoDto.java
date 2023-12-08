package com.selfman.provider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ContactInfoDto {
	String country;
	String city;
	String street;
	String building;
	String zipCode;
	String phoneNumber;
	String email;
	String website;
}
