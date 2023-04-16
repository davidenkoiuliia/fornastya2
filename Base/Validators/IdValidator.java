package Base.Validators;

import Base.Handlers.CollectionHandler;
import Base.Handlers.RoutesHandler;
import Base.Route;

import java.util.HashSet;
import java.util.TreeSet;

public class IdValidator implements Validator<Long> {
    TreeSet<Long> ids;

    public IdValidator()
    {
        ids = new TreeSet<>();

        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();

        handler.getCollection().forEach((value) -> ids.add(value.getId()));
    }
    @Override
    public boolean validate(Long value) {

        if (value == null) return false;
        if (value <= 0) return false;
        return ids.add(value);
    }
}
