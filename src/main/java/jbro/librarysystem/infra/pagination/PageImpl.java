package jbro.librarysystem.infra.pagination;

import java.util.List;

public class PageImpl<T> implements Page<T> {
    private final List<T> content;
    private final Pageable pageable;
    private final int total;

    public PageImpl(List<T> content, Pageable pageable, int total) {
        this.content = content;
        this.pageable = pageable;
        this.total = total;
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) pageable.getPageSize());
    }

    @Override
    public int getTotal() {
        return total;
    }

    @Override
    public Pageable getPageable() {
        return pageable;
    }

    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    public int getSize() {
        return pageable.getPageSize();
    }
}
