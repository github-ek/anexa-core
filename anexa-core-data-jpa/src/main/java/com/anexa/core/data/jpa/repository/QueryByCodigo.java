package com.anexa.core.data.jpa.repository;

import java.util.Optional;

import com.anexa.core.domain.ObjectWithCode;

public interface QueryByCodigo<E extends ObjectWithCode<ID>, ID>{
	Optional<E> findByCodigo(String codigo);
}