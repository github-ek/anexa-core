package com.anexa.core.services.crud.api;

import java.util.List;
import java.util.Optional;

import com.anexa.core.domain.IdentifiedDomainObject;

public interface QueryService<M extends IdentifiedDomainObject<ID>, ID> {

	M findOneById(ID id);

	Optional<M> findById(ID id);

	List<M> findAllById(List<ID> ids);
}