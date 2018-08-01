package com.anexa.core.services.crud.api;

import java.util.Optional;

import com.anexa.core.domain.ObjectWithCode;

public interface QueryByCodigoService<M extends ObjectWithCode<ID>, ID> {

	Optional<M> findByCodigo(String codigo);

}