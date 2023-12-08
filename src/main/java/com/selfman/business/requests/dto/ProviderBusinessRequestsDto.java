package com.selfman.business.requests.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProviderBusinessRequestsDto {
       String id;
       String customerEmail;
       String productDescription;
       List<String> files;
       String status;
       LocalDateTime dateTime;
       
}