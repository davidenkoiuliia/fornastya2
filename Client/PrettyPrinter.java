package Client;

import Models.Band;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class PrettyPrinter {
    static final String keyFormat = "%5s |";
    static final String bandIDFormat = "%5s |";
    static final String bandNameFormat = "%15s |";
    static final String XFormat = "%7s |";
    static final String YFormat = "%7s |";
    static final String dateFormat = "%15s |";
    static final String albumsCountFormat = "%7s |";
    static final String genreFormat = "%9s |";
    static final String numberOfParticipantsFormat = "%7s |";
    static final String studioName = "%12s |";
    static final String studioAddress = "%20s |";
    static void printBandHashMap(HashMap<Integer, Band> hashMap) {
        System.out.printf(keyFormat, "Key");
        printBandParamNames();
        System.out.println("-".repeat(165));

        for (Integer key : hashMap.keySet()) {
            Band band = hashMap.get(key);
            System.out.printf(keyFormat, key);
            printBandParams(band);
        }
    }
    private static void printBandParamNames() {
        System.out.printf(bandIDFormat, "ID");
        System.out.printf(bandNameFormat, "bandName");
        System.out.printf(XFormat, "coordX");
        System.out.printf(YFormat, "coordY");
        System.out.printf(dateFormat, "creationDate");
        System.out.printf(numberOfParticipantsFormat, "number of participants");
        System.out.printf(genreFormat, "genre");
        System.out.printf(albumsCountFormat, "albums count");
        System.out.printf(studioName, "stdName");
        System.out.printf(studioAddress, "stdAddress");
        System.out.println();
    }

    private static void printBandParams(Band band) {
        System.out.printf(bandIDFormat, band.getId());
        System.out.printf(bandNameFormat, band.getName());
        System.out.printf(XFormat, band.getCoordinates().getX());
        System.out.printf(YFormat, band.getCoordinates().getY());
        System.out.printf(dateFormat, band.getCreationDate().format(DateTimeFormatter.ofPattern(dateFormat)));
        System.out.printf(albumsCountFormat, band.getAlbumsCount());
        System.out.printf(genreFormat, band.getGenre());
        System.out.printf(numberOfParticipantsFormat, band.getNumberOfParticipants());
        System.out.printf(studioName, band.getStudio().getName());
        System.out.printf(studioAddress, band.getStudio().getAddress());
        System.out.println();
    }
}