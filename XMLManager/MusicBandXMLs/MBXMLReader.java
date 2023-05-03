package XMLManager.MusicBandXMLs;

import Exceptions.IO.*;
import Models.*;
import Reciever.MusicBandCollection;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class MBXMLReader implements MusicBandFileReader {
    private final String path;

    public MBXMLReader(String path) {
        this.path = path;
    }

    @Override
    public MusicBandCollection read() throws FileNotFoundException, FilePermissionException, InvalidFileDataException {
        checkFile();
        String attributeName = null;
        int i = -1;
        try {
            MusicBandCollection bandCollection = new MusicBandCollection();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(path);
            if (file.length() == 0) {
                System.out.println("Created empty band collection.");
                bandCollection.setCreationDate(ZonedDateTime.now());
                return bandCollection;
            }
            Document document = builder.parse(file);
            document.getDocumentElement().normalize();

            attributeName = "collectionCreationDate";
            String collectionCreationDateInput = document.getDocumentElement().getAttribute(attributeName);
            ZonedDateTime collectionCreationDate = ZonedDateTime.parse(collectionCreationDateInput);

            attributeName = "music band";
            NodeList movieElements = document.getDocumentElement().getElementsByTagName(attributeName);
            for (i = 0; i < movieElements.getLength(); i++) {
                Element bandElement = (Element) movieElements.item(i);

                attributeName = "id";
                String idInput = bandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int id = Integer.parseInt(idInput);

                attributeName = "bandName";
                String bandName = bandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();

                attributeName = "coordinates";
                Element coordinatesInput = (Element) bandElement.getElementsByTagName(attributeName).item(0);
                if (coordinatesInput == null)
                    throw new NullPointerException();
                attributeName = "x";
                String xInput = coordinatesInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int x = Integer.parseInt(xInput);
                attributeName = "y";
                String yInput = coordinatesInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                float y = Float.parseFloat(yInput);
                Coordinates coordinates = new Coordinates(x,y);

                attributeName = "CreationDate";
                String creationDateInput = bandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                LocalDateTime creationDate = LocalDateTime.parse(creationDateInput);

                attributeName = "numberOfParticipants";
                String numberOfParticipantsInput = bandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                long numberOfParticipants = Long.parseLong(numberOfParticipantsInput);

                attributeName = "albumsCost";
                String albumsCostInput = bandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                int albumsCost = Integer.parseInt(numberOfParticipantsInput);

                attributeName = "studio";
                Element studioInput = (Element) bandElement.getElementsByTagName(attributeName).item(0);
                attributeName = "studioName";
                String studioNameInput = studioInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                attributeName = "studioAddress";
                String studioAddressInput = studioInput.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                Studio studio=new Studio(studioNameInput, studioAddressInput);
                //органичение на адрес студии?
                attributeName = "genre";
                String genreInput = bandElement.getElementsByTagName(attributeName).item(0).getTextContent().trim();
                MusicGenre genre;
                try {
                    genre = MusicGenre.valueOf(genreInput);
                } catch (IllegalArgumentException e) {
                    throw new WrongArgumentException("wrong genre");
                }
                if (bandCollection.getElementByID(id) != null)
                    throw new WrongArgumentException("music band id must be unique");

                Band band = new Band(id, bandName, coordinates, albumsCost, (int) numberOfParticipants, genre, studio);
                bandCollection.put(id, band);
            }
            bandCollection.setCreationDate(collectionCreationDate);
            Band.updateNextId(bandCollection);
            return bandCollection;
        } catch (NullPointerException e) {
            throw new InvalidFileDataException(path, "band №" + (i + 1) + ": " + attributeName + " is null");
        } catch (DateTimeParseException e) {
            if (i < 0) {
                throw new InvalidFileDataException(path, attributeName + " is invalid or null");
            } else {
                throw new InvalidFileDataException(path, "band №" + (i + 1) + ": " + attributeName + " is invalid or null");
            }
        } catch (UnsupportedEncodingException e) {
            throw new InvalidFileDataException(path, "unsupported encoding: " + e.getMessage());
        } catch (SAXParseException e) {
            throw new InvalidFileDataException(path, "XML parse error: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new InvalidFileDataException(path, "band №" + (i + 1) + ": " + attributeName + " must be an integer");
        } catch (WrongArgumentException e) {
            StringBuilder errorMessage = new StringBuilder(e.getMessage());
            errorMessage.delete(0, 2);
            errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
            throw new InvalidFileDataException(path, "band insert №" + (i + 1) + ": " + errorMessage);
        } catch ( IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e.getClass());
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new InvalidFileDataException(path, e.getMessage());
        }
    }

    private void checkFile() throws FileNotFoundException, FilePermissionException {
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("! file " + path + " not found !");
        if (!file.canRead())
            throw new FilePermissionException("! no read permission for file " + path + "  !");
    }
}