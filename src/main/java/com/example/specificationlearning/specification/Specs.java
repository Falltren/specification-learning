package com.example.specificationlearning.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface Specs {

    private static <T, V> Specification<T> isEmptyValue(V value, Supplier<Specification<T>> supplier) {
        if ((value == null) || (value instanceof String str && str.isEmpty()) ||
                (value instanceof Integer num && num < 1) || value instanceof List<?> list && list.isEmpty() ||
                (value instanceof Map<?, ?> map && map.size() < 2)) {
            return (r, q, c) -> null;
        }
        return supplier.get();
    }

    static <T, V> Specification<T> eq(SingularAttribute<T, V> field, V value) {
        return isEmptyValue(value, () -> (root, query, cb) -> {
            if (value instanceof String str) {
                return cb.equal(cb.lower(root.get(field.getName())), str.toLowerCase());
            }
            return cb.equal(root.get(field), value);
        });
    }
}
