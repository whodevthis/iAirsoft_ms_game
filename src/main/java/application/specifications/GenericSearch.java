package application.specifications;


import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GenericSearch {

    public static <T> Specification<T> search(String term, Class<T> entityClass) {
        return (root, query, cb) -> {
            if (term == null || term.isBlank()) return cb.conjunction();

            String pattern = "%" + term.toLowerCase() + "%";
            List<Predicate> predicates = new ArrayList<>();

            for (Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.getType() == String.class) {
                        predicates.add(cb.like(cb.lower(root.get(field.getName())), pattern));
                    } else if (field.getType().isEnum()) {
                        predicates.add(cb.like(cb.lower(root.get(field.getName()).as(String.class)), pattern));
                    } else if (field.getType() == UUID.class) {
                        predicates.add(cb.like(root.get(field.getName()).as(String.class), "%" + term + "%"));
                    }
                } catch (Exception ignored) {}
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}