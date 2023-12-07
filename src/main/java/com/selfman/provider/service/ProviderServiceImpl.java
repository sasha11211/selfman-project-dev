package com.selfman.provider.service;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.selfman.provider.dao.ProviderRepository;
import com.selfman.provider.dto.ProviderCreateDto;
import com.selfman.provider.dto.ProviderDto;
import com.selfman.provider.dto.ProviderRegisterDto;
import com.selfman.provider.dto.ProviderRemoveDto;
import com.selfman.provider.dto.ProviderUpdateDto;
import com.selfman.provider.dto.SocialMediaDto;
import com.selfman.provider.exceptions.ProviderExistsExeption;
import com.selfman.provider.exceptions.ProviderNotFoundException;
import com.selfman.provider.model.ContactInfo;
import com.selfman.provider.model.Provider;
import com.selfman.provider.model.SocialMedia;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService, CommandLineRunner {

	final ProviderRepository providerRepository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;

	@Override
	public ProviderCreateDto createProvider(ProviderRegisterDto providerRegisterDto) {
		if (providerRepository.existsById(providerRegisterDto.getEmail())) {
			throw new ProviderExistsExeption();
		}
		Provider provider = modelMapper.map(providerRegisterDto, Provider.class);
		String password = passwordEncoder.encode(providerRegisterDto.getPassword());
		provider.setPassword(password);
		provider.addRole("PROVIDER");
		providerRepository.save(provider);
		return modelMapper.map(provider, ProviderCreateDto.class);
	}

	@Override
	public ProviderDto updateProvider(String email, ProviderUpdateDto providerUpdateDto) {
		Provider provider = providerRepository.findById(email).orElseThrow(ProviderNotFoundException::new);
		provider.setLogo(provider.getLogo());
		provider.setName(providerUpdateDto.getName());
		provider.setLanguages(providerUpdateDto.getLanguages());
		provider.setIndustry(providerUpdateDto.getIndustry());
		provider.setKeywords(providerUpdateDto.getKeywords());
		provider.setProducts(providerUpdateDto.getProducts());
		provider.setFounded(providerUpdateDto.getFounded());

		Set<SocialMediaDto> socialMediaDtoSet = providerUpdateDto.getSocialMedia();
		socialMediaDtoSet.stream().forEach(System.out::println);
//		for (SocialMediaDto socialMediaDto : socialMediaDtoSet) {
//			
//			provider.addSocialMedia(modelMapper.map(socialMediaDto, SocialMedia.class));
//		}

		if (provider.getName() != null && provider.getIndustry() != null && provider.getProducts() != null) {
			provider.addRole("VERIFIED");
		}
		providerRepository.save(provider);
		return modelMapper.map(provider, ProviderDto.class);
	}

	@Override
	public ProviderRemoveDto removeProvider(String email) {
		Provider provider = providerRepository.findById(email).orElseThrow(ProviderNotFoundException::new);
		providerRepository.delete(provider);
		return modelMapper.map(provider, ProviderRemoveDto.class);
	}

	@Override
	public void changePasswordProvider(String email, String newPassword) {
		Provider provider = providerRepository.findById(email).orElseThrow(ProviderNotFoundException::new);
		String password = passwordEncoder.encode(newPassword);
		provider.setPassword(password);
		providerRepository.save(provider);
	}

	@Override
	public ProviderDto getProvider(String email) {
		Provider provider = providerRepository.findById(email).orElseThrow(ProviderNotFoundException::new);
		return modelMapper.map(provider, ProviderDto.class);
	}

	@Override
	public void run(String... args) throws Exception {
		if (providerRepository.findById("admin").isEmpty()) {
			String password = passwordEncoder.encode("admin");
			Provider provider = new Provider("admin", "admin", password, "");
			provider.addRole("ADMINISTRATOR");
			providerRepository.save(provider);
		}
	}

}
