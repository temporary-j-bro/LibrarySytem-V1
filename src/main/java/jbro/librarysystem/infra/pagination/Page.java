package jbro.librarysystem.infra.pagination;

import java.util.List;

public interface Page<T> {
    List<T> getContent();
    int getTotalPages();
    int getTotal();
    int getPageNumber();
    int getSize();
    Pageable getPageable();
}
