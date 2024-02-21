package com.jdc.home.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ParentEntity {

	@CreatedDate
	private LocalDateTime createAt;
	@LastModifiedDate
	private LocalDateTime updateAt;
	
	@CreatedBy
	private String createBy;
	@LastModifiedBy
	private String updateBy;
	
	
}
