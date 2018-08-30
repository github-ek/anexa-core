package com.anexa.core.services.crud.api;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.anexa.core.domain.ObjectWithCode;

public interface QueryByCodigoService<M extends ObjectWithCode<ID>, ID> {

	@Transactional(readOnly = true)
	Optional<M> findByCodigo(String codigo);

}