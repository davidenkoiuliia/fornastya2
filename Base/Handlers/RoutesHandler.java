package Base.Handlers;

import Base.Comparators.RouteComparator;
import Base.Route;
import Base.Validators.RouteValidator;
import Base.Validators.Validator;
import java.time.Instant;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Date;
import java.util.Iterator;

public class RoutesHandler implements CollectionHandler<HashSet<Route>, Route> {
        private static RoutesHandler singletoneMoment;
        private HashSet<Route> routes;
        private final Date initDate;

    private RoutesHandler() {
            routes = new HashSet<>();
            initDate = Date.from(Instant.now());
        }
        public static RoutesHandler getInstance() {
            if (singletoneMoment == null)
                singletoneMoment = new RoutesHandler();
            return singletoneMoment;
        }
        @Override
        public HashSet<Route> getCollection()
        {
            return routes;
        }
        @Override
        public void setCollection(HashSet<Route> routes) {
            this.routes = routes;
            validateElements();
            sort();
        }
        @Override
        public void addElementToCollection(Route e)
        {
            routes.add(e);
            sort();
        }
        @Override
        public void clearCollection() {
            routes.clear();
        }
        @Override
        public void sort() {
            HashSet<Route> sorted = new HashSet<>();
            for (Iterator<Route> it = routes.stream().sorted(new RouteComparator()).iterator(); it.hasNext(); ) {
                Route sortedItem = it.next();

                sorted.add(sortedItem);
            }

            this.routes = sorted;
        }
        @Override
        public Route getFirstOrNew()
        {
            if (routes.iterator().hasNext())
                return routes.iterator().next();
            else
                return new Route();
        }
        @Override
        public Date getInitDate() {
            return initDate;
        }
        @Override
        public Route getLastElement()
        {
            Route result = null;
            for (Route route : routes) {
                result = route;
            }
            return result;
        }
        @Override
        public void validateElements() {
            HashSet<Long> ids = new HashSet<>(getCollection().size());

            for (Iterator<Route> it = getCollection().iterator(); it.hasNext(); ) {
                Route toValid = it.next();
                Validator<Route> validator = new RouteValidator();

                if (!validator.validate(toValid) || !ids.add(toValid.getId()))
                {
                    it.remove();
                    System.out.println("Element removed from collection: " + toValid);
                    System.out.println("This element violates the restriction of some fields. Check your file and fix it manually.");
                }
            }
        }
        @Override
        public Route getMin(Comparator<Route> comparator) {

            return getCollection().stream().min(comparator).orElse(null);
        }
        @Override
        public Route getMax(Comparator<Route> comparator) {
            return getCollection().stream().max(comparator).orElse(null);
        }
    }