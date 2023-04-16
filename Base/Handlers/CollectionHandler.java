package Base.Handlers;

import java.util.Comparator;
import java.util.Date;
import java.util.AbstractCollection;

public interface CollectionHandler <T extends AbstractCollection<E>, E> {
    T getCollection();
    void setCollection(T value);
    void addElementToCollection(E value);
    void clearCollection();
    void sort();
    E getFirstOrNew();
    Date getInitDate();
    E getLastElement();
    void validateElements();
    E getMin(Comparator<E> comparator);
    E getMax(Comparator<E> comparator);
}