package com.egakat.core.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.egakat.core.domain.ObjectAuditableByTime;
import com.egakat.core.domain.VersionableObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class SimpleAuditableEntityDto<ID> extends EntityDto<ID>
		implements VersionableObject, ObjectAuditableByTime {

	@NumberFormat
	private int version;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaCreacion;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;
}
