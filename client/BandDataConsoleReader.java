package Client;

import Exceptions.Client.InvalidScriptException;
import Exceptions.IO.WrongArgumentException;
import Models.MusicGenre;
import XMLManager.Basic.BasicReader;
import Models.Helpers.BandArgumentChecker;

public class BandDataConsoleReader {
    public static String readBandName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String bandName = null;
        while (bandName == null) {
            try {
                String input = basicReader.readLine("Enter movie name");
                bandName = input.trim();
                BandArgumentChecker.checkName(bandName);
            } catch (WrongArgumentException e) {
                bandName = null;
                if (inScriptMode) {
                    throw new InvalidScriptException(e.getMessage());
                } else {
                    System.out.println(e.getMessage());
                }
            }
        }
        return bandName;
    }

    public static Integer readX(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer x = null;
        boolean xSuccess = false;
        while (!xSuccess) {
            try {
                String input = basicReader.readLine("Enter X coordinate");
                String xInput = input.trim();
                x = Integer.parseInt(xInput);
                xSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return x;
    }

    public static Float readY(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Float y = null;
        boolean ySuccess = false;
        while (!ySuccess) {
            try {
                String input = basicReader.readLine("Enter Y coordinate");
                String xInput = input.trim();
                y = Float.parseFloat(xInput);
                ySuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an float !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return y;
    }

    public static int readAlbumsCount(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        int albumsCount = 0;
        boolean albumsCountSuccess = false;
        while (!albumsCountSuccess) {
            try {
                String input = basicReader.readLine("Enter albums count");
                String albumsCountInput = input.trim();
                albumsCount = Integer.parseInt(albumsCountInput);
                BandArgumentChecker.checkAlbumsCount(albumsCount);
                albumsCountSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            } catch (WrongArgumentException e) {
                String errorMessage = e.getMessage();
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return albumsCount;
    }

    public static MusicGenre readBandGenre(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        MusicGenre musicGenre = null;

        StringBuilder message = new StringBuilder("Enter music genre (");
        for (MusicGenre genre : MusicGenre.values()) {
            message.append(genre.toString());
            message.append("; ");
        }
        message.delete(message.length() - 2, message.length());
        message.append(")");

        while (musicGenre == null) {
            String input = basicReader.readLine(String.valueOf(message));
            String musicGenreInput = input.trim();
            try {
                musicGenre = MusicGenre.valueOf(musicGenreInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                String errorMessage = "! wrong music genre !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return musicGenre;
    }

    public static String readStudioName(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        String studioName = null;
        while (studioName == null) {
            String input = basicReader.readLine("Enter studio name");
            studioName = input.trim();
        }
        return studioName;
    }

    public static String readAddress(BasicReader basicReader, boolean inScriptMode) {
        String address = null;
        while (address == null) {
            String input = basicReader.readLine("Enter studio address (the name of the city)");
            String addressInput = input.trim();
        }
        return address;
    }

    public static Integer numberOfParticipants(BasicReader basicReader, boolean inScriptMode) throws InvalidScriptException {
        Integer num = null;
        boolean numSuccess = false;
        while (!numSuccess) {
            try {
                String input = basicReader.readLine("Enter the number of participants");
                String numInput = input.trim();
                if (!numInput.equals("")) {
                    num = Integer.parseInt(numInput);
                }
                numSuccess = true;
            } catch (NumberFormatException e) {
                String errorMessage = "! not an integer !";
                if (inScriptMode) {
                    throw new InvalidScriptException(errorMessage);
                } else {
                    System.out.println(errorMessage);
                }
            }
        }
        return num;
    }
}
