package Base.Handlers;

import Base.Route;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import Base.Validators.Validator;
import Base.Validators.IdValidator;

public class RouteHandler {
    public static Long generateID() {
        CollectionHandler<HashSet<Route>, Route> handler = RoutesHandler.getInstance();
        Validator<Long> idValidator = new IdValidator();
        var lastObj = handler.getLastElement();
        long lastId = 1L;
        if (lastObj != null) {
            lastId = lastObj.getId() + 1;
        }
        while (!idValidator.validate(lastId)) {
            lastId = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        }
        System.out.println("ID Field (auto-generated): " + lastId);
        return lastId;
    }
}
