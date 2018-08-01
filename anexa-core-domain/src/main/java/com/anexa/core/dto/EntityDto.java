package com.anexa.core.dto;

import com.anexa.core.domain.IdentifiedDomainObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityDto<ID> implements IdentifiedDomainObject<ID> {

	private ID id;

}
