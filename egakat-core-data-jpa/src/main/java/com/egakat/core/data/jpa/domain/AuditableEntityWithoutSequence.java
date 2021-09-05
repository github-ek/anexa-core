package com.egakat.core.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.egakat.core.domain.ObjectAuditableByUser;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntityWithoutSequence<ID> extends SimpleAuditableEntity<ID>
		implements ObjectAuditableByUser {

	@Column(nullable = false, updatable = false)
	@NotNull
	@CreatedBy
	private String creadoPor;

	@Column(nullable = false)
	@NotNull
	@LastModifiedBy
	private String modificadoPor;
}
