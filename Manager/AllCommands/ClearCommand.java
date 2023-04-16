package Manager.AllCommands;

import Base.Handlers.CollectionHandler;
import Base.Handlers.RoutesHandler;
import Base.Route;

import java.util.HashSet;

public class ClearCommand implements Command {
    @Override
    public String getName() {
    return "clear";
}
    @Override
    public String getDescr() {
        return "Очищает коллекцию";
    }

    @Override
    public void execute(String[] args) {
        CollectionHandler<HashSet<Route>, Route> collectionHandler = RoutesHandler.getInstance();
        collectionHandler.clearCollection();
        System.out.println("Очищено!");
    }
}
