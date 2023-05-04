package Reciever;

import Models.Band;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MusicBandCollection {
    private HashMap<Integer, Band> bandHashMap = new HashMap<>();
    private java.time.ZonedDateTime creationDate = ZonedDateTime.now();
    public void put(Integer key, Band band) {
        bandHashMap.put(key, band);
    }
    public Band getElementByKey(Integer key) {
        return bandHashMap.get(key);
    }
    public Band getElementByID(Integer id) {
        for (Band band : bandHashMap.values()) {
            if (Objects.equals(band.getId(), id))
                return band;
        }
        return null;
    }
    public void remove(Integer key) {
        bandHashMap.remove(key);
    }
    public void clear() {
        bandHashMap.clear();
    }
    public HashMap<Integer, Band> getBandHashMap() {
        return bandHashMap;
    }
    public int length() {
        return bandHashMap.size();
    }
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public int removeLower(Band band) {
        HashMap<Integer, Band> newBandHashMap = new HashMap<>(bandHashMap);
        int count = 0;
        for (Integer key : bandHashMap.keySet()) {
            if (getElementByKey(key).compareTo(band) < 0) {
                newBandHashMap.remove(key);
                count += 1;
            }
        }
        bandHashMap = newBandHashMap;
        return count;
    }
    public boolean replaceIfGreater(Integer key, Band band) {
        if (getElementByKey(key).compareTo(band) < 0) {
            put(key, band);
            return true;
        }
        return false;
    }
    public int removeLowerKey(Integer key) {
        HashMap<Integer, Band> newBandHashMap = new HashMap<>(bandHashMap);
        int count = 0;
        for (Integer i : bandHashMap.keySet()) {
            if (i < key) {
                newBandHashMap.remove(i);
                count += 1;
            }
        }
        bandHashMap = newBandHashMap;
        return count;
    }
    public int count_by_albums(int albumsCount) {
        int count = 0;
        for (Band band : bandHashMap.values()) {
            if (band.getAlbumsCount() == albumsCount) {
                count += 1;
            }
        }
        return count;
    }
    public HashMap<Integer, Band> filter_starts_with_name(String name) {
        HashMap<Integer, Band> newBandHashMap = new HashMap<>();
        for (Map.Entry<Integer, Band> band : bandHashMap.entrySet()) {
            if (band.getValue().getName().startsWith(name)) {
                newBandHashMap.put(band.getKey(), band.getValue());
            }
        }
        return newBandHashMap;
    }
}