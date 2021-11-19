package com.egakat.core.data.jpa.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.egakat.core.domain.ObjectAuditableByTime;
import com.egakat.core.domain.VersionableObject;

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
public abstract class SimpleAuditableEntity<ID> extends BaseEntity<ID>
		implements VersionableObject, ObjectAuditableByTime {

	@Version
	private int version;

	@Column(nullable = false, updatable = false)
	@NotNull
	@CreatedDate
	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaCreacion;

	@Column(nullable = false)
	@NotNull
	@LastModifiedDate
	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;
}
