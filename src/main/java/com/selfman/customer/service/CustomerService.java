package com.selfman.customer.service;

import com.selfman.customer.dto.CustomerDto;
import com.selfman.customer.dto.CustomerExtendedDto;
import com.selfman.customer.dto.CustomerRegisterDto;
import com.selfman.customer.dto.CustomerRemoveDto;
import com.selfman.customer.dto.RolesCustomerDto;

public interface CustomerService {
	CustomerDto registerCustomer(CustomerRegisterDto customerRegisterDto);
	
	CustomerExtendedDto getCustomer(String email);

	CustomerRemoveDto removeCustomer(String email);

	CustomerExtendedDto updateCustomer(String email, CustomerExtendedDto customerExtendedDto);
	
	RolesCustomerDto changeRolesListCustomer(String email, String role, boolean isAddRole);

	void changePasswordCustomer(String email, String newPassword);

}
