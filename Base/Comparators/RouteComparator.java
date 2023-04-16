package Base.Comparators;

import Base.Route;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        return Long.compare(o1.getId(), o2.getId());
    }
}
