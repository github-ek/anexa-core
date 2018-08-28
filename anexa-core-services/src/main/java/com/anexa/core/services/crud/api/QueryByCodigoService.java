package com.anexa.core.services.crud.api;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.anexa.core.domain.ObjectWithCode;

@Transactional(readOnly = true)
public interface QueryByCodigoService<M extends ObjectWithCode<ID>, ID> {

	Optional<M> findByCodigo(String codigo);

}