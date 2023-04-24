package Reciever;

import Models.Band;

import java.time.ZonedDateTime;
import java.util.*;

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
    public int removeGreater(Band band) {
        HashMap<Integer, Band> newBandHashMap = new HashMap<>(bandHashMap);
        int count = 0;
        for (Integer key : bandHashMap.keySet()) {
            if (getElementByKey(key).compareTo(band) > 0) {
                newBandHashMap.remove(key);
                count += 1;
            }
        }
        bandHashMap = newBandHashMap;
        return count;
    }
    public boolean replaceIfLower(Integer key, Band band) {
        if (getElementByKey(key).compareTo(band) > 0) {
            put(key, band);
            return true;
        }
        return false;
    }
    public int removeLowerKey(Integer key) {
        HashMap<Integer, Band> newMovieHashMap = new HashMap<>(bandHashMap);
        int count = 0;
        for (Integer i : bandHashMap.keySet()) {
            if (i < key) {
                newMovieHashMap.remove(i);
                count += 1;
            }
        }
        bandHashMap = newMovieHashMap;
        return count;
    }
    public List<Band> printAscending() {
        List<Band> bandList = new ArrayList<>(bandHashMap.values());
        Collections.sort(bandList);
        return bandList;
    }
    public List<Band> printDescending() {
        List<Band> bandList = new ArrayList<>(bandHashMap.values());
        Collections.sort(bandList);
        Collections.reverse(bandList);
        return bandList;
    }
    public List<Band> printFieldDescendingNumbersOfParticipants() {
        List<Band> bandList = new ArrayList<>(bandHashMap.values());
        bandList.sort(new Comparator<>() {
            public int compare(Band o1, Band o2) {
                return (int) (o2.getNumberOfParticipants() - o1.getNumberOfParticipants());
            }
        });
        return bandList;
    }
}
