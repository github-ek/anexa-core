package com.anexa.core.services.crud.api;

import java.util.List;
import java.util.Map;

import com.anexa.core.domain.IdentifiedDomainObject;


public interface CrudService<M extends IdentifiedDomainObject<ID>, ID>
		extends QueryService<M, ID> {

	M create(M model);

	List<M> create(List<M> models);

	M update(M model);

	List<M> update(List<M> models);

	void delete(ID id);

	void delete(List<ID> ids);

	void delete(ID id, int version);

	void delete(Map<ID, Integer> models);
}