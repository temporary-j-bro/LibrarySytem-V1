package jbro.librarysystem.infra.pagination;

public interface Pageable {
    int getPageNumber();
    int getPageSize();
    int getOffset();
}
