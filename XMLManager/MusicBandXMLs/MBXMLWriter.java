package XMLManager.MusicBandXMLs;

import Exceptions.IO.CustomIOException;
import Exceptions.IO.FilePermissionException;
import Models.Band;
import Reciever.MusicBandCollection;

import java.io.*;
import java.util.HashMap;

public class MBXMLWriter implements MusicBandFileWriter {

    private final String path;

    public MBXMLWriter(String path) {
        this.path = path;
    }

    public void write(MusicBandCollection bandCollection) throws FileNotFoundException, FilePermissionException, CustomIOException {
        checkFile();
        HashMap<Integer, Band> bandHashMap = bandCollection.getBandHashMap();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bufferedWriter.write("<bandCollection collectionCreationDate=\"" + bandCollection.getCreationDate() + "\">\n");
            for (Band band : bandHashMap.values()) {
                String indent = "    ";
                bufferedWriter.write(indent.repeat(1) + "<band>\n");
                bufferedWriter.write(indent.repeat(2) + "<id>" + band.getId() + "</id>\n");
                bufferedWriter.write(indent.repeat(2) + "<bandName>" + band.getName() + "</bandName>\n");
                bufferedWriter.write(indent.repeat(2) + "<coordinates>\n");
                bufferedWriter.write(indent.repeat(3) + "<x>" + band.getCoordinates().getX() + "</x>\n");
                bufferedWriter.write(indent.repeat(3) + "<y>" + band.getCoordinates().getY() + "</y>\n");
                bufferedWriter.write(indent.repeat(2) + "</coordinates>\n");
                bufferedWriter.write(indent.repeat(2) + "<numberOfParticipants>" + band.getNumberOfParticipants() + "</numberOfParticipants>\n");
                bufferedWriter.write(indent.repeat(2) + "<albumsCount>" + band.getAlbumsCount() + "</albumsCount>\n");
                bufferedWriter.write(indent.repeat(2) + "<genre>" + band.getGenre() + "</genre>\n");
                bufferedWriter.write(indent.repeat(2) + "<studio>\n");
                bufferedWriter.write(indent.repeat(3) + "<studioName>" + band.getStudio().getName() + "</studioName>\n");
                bufferedWriter.write(indent.repeat(3) + "<studioAddress>" + band.getStudio().getAddress() + "</studioAddress>\n");
                bufferedWriter.write(indent.repeat(2) + "</studio>\n");
                bufferedWriter.write(indent.repeat(1) + "</band>\n");
            }
            bufferedWriter.write("</bandCollection>");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new CustomIOException("! " + this.getClass().getName() + ": IOException when writing to " + path + " !");
        }
    }

    private void checkFile() throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canWrite())
            throw new FilePermissionException("! no write permission for file " + path + "  !");
    }
}