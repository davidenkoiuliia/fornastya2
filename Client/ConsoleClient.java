package Client;

import Commands.*;
import Exceptions.Client.*;
import Exceptions.IO.*;
import Exceptions.Receiver.CollectionKeyException;
import Models.Band;
import Models.Helpers.BandArgumentChecker;
import Models.MusicGenre;
import Reciever.Receiver;
import XMLManager.Basic.BasicReader;
import XMLManager.Basic.ConsoleReader;
import XMLManager.Basic.CustomFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Stack;

import static Client.BandDataConsoleReader.*;

public class ConsoleClient implements Client {
    Invoker invoker;
    Receiver receiver;
    private final Stack<String> pathStack = new Stack<>();
    private boolean canExit = false;
    public void main() {
        try {
            // Initialize Invoker, Receiver and Reader
            invoker = new Invoker();
            receiver = new Receiver();
            BasicReader consoleReader = new ConsoleReader();

            System.out.println("Data loaded successfully. You are now in interactive mode\nType 'help' to see the list of commands\n");

            while (!canExit) {
                try {
                    readAndExecuteCommand(consoleReader);
                } catch (InvalidCommandException | CollectionKeyException | WrongNumberOfArgumentsException |
                         WrongArgumentException | InvalidScriptException | CustomIOException e) {
                    System.out.println(e.getMessage());
                }
            }

        } catch (NullPointerException e) {
            // Handle NullPointerException thrown when LAB5 environment variable is not set
            System.out.println(e.getMessage());
            System.out.println("! path variable is null !");
            System.exit(0);
        } catch (InvalidFileDataException | EndOfInputException | FilePermissionException | FileNotFoundException e) {
            // Handle exceptions thrown when there is a problem with the data file or the user input
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void readAndExecuteCommand(BasicReader basicReader) throws InvalidCommandException, CollectionKeyException,
            WrongNumberOfArgumentsException, WrongArgumentException, InvalidScriptException, CustomIOException {
        String input = basicReader.readLine().trim();
        if (input.startsWith("//") || input.equals("")) {
            return;
        }
        String[] inputArray = input.split("\s+");
        String commandName = inputArray[0].toLowerCase();

        String[] args = new String[inputArray.length - 1];
        System.arraycopy(inputArray, 1, args, 0, inputArray.length - 1);

        switch (commandName) {
            case "help" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Help(this, receiver));
            }
            case "history" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new History(this, receiver));
            }
            case "info" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                String result = invoker.executeAndReturn(new Info(this, receiver));
                System.out.println(result);
            }
            case "show" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                HashMap<Integer, Band> result = invoker.executeAndReturn(new Show(this, receiver));
                PrettyPrinter.printBandHashMap(result);
            }
            case "insert" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    boolean inScriptMode = inScriptMode();
                    Integer key = Integer.parseInt(args[0]);
                    BandArgumentChecker.checkKey(key);
                    String bandName = readBandName(basicReader, inScriptMode);
                    Integer x = readX(basicReader, inScriptMode);
                    Float y = readY(basicReader, inScriptMode);
                    int albumsCount = readAlbumsCount(basicReader, inScriptMode);
                    MusicGenre musicGenre = readBandGenre(basicReader, inScriptMode);
                    String StudioName = readStudioName(basicReader, inScriptMode);
                    String StudioAddress = readAddress(basicReader, inScriptMode);
                    Integer numberOfParticipants = numberOfParticipants(basicReader, inScriptMode);
                    invoker.execute(new Insert(this, receiver, key, bandName, x, y, albumsCount,
                            musicGenre, numberOfParticipants, StudioName, StudioAddress));
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "update" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    boolean inScriptMode = inScriptMode();
                    Integer key = Integer.parseInt(args[0]);
                    String bandName = readBandName(basicReader, inScriptMode);
                    Integer x = readX(basicReader, inScriptMode);
                    Float y = readY(basicReader, inScriptMode);
                    int albumsCount = readAlbumsCount(basicReader, inScriptMode);
                    MusicGenre musicGenre = readBandGenre(basicReader, inScriptMode);
                    String StudioName = readStudioName(basicReader, inScriptMode);
                    String StudioAddress = readAddress(basicReader, inScriptMode);
                    Integer numberOfParticipants = numberOfParticipants(basicReader, inScriptMode);
                    invoker.execute(new Update(this, receiver, key, bandName, x, y, albumsCount,
                            musicGenre, numberOfParticipants, StudioName, StudioAddress));
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "remove_key" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    BandArgumentChecker.checkKey(key);
                    invoker.execute(new RemoveKey(this, receiver, key));
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "clear" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Clear(this, receiver));
            }
            case "save" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Save(this, receiver));
            }
            case "execute_script" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                String path = args[0];
                invoker.execute(new ExecuteScript(this, receiver, path));
            }
            case "exit" -> {
                if (args.length != 0)
                    throw new WrongNumberOfArgumentsException();
                invoker.execute(new Exit(this, receiver));
            }
            case "replace_if_greater" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    boolean inScriptMode = inScriptMode();
                    Integer key = Integer.parseInt(args[0]);
                    BandArgumentChecker.checkKey(key);
                    String bandName = readBandName(basicReader, inScriptMode);
                    Integer x = readX(basicReader, inScriptMode);
                    Float y = readY(basicReader, inScriptMode);
                    int albumsCount = readAlbumsCount(basicReader, inScriptMode);
                    MusicGenre musicGenre = readBandGenre(basicReader, inScriptMode);
                    String StudioName = readStudioName(basicReader, inScriptMode);
                    String StudioAddress = readAddress(basicReader, inScriptMode);
                    Integer numberOfParticipants = numberOfParticipants(basicReader, inScriptMode);
                    invoker.execute(new ReplaceIfGreater(this, receiver, key, bandName, x, y, albumsCount,
                            musicGenre, numberOfParticipants, StudioName, StudioAddress));
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "remove_lower_key" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer key = Integer.parseInt(args[0]);
                    BandArgumentChecker.checkKey(key);
                    invoker.execute(new RemoveLowerKey(this, receiver, key));
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "count_by_albums" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                try {
                    Integer albumsCount = Integer.parseInt(args[0]);
                    BandArgumentChecker.checkAlbumsCount(albumsCount);
                    invoker.execute(new CountByAlbums(this, receiver, albumsCount));
                } catch (NumberFormatException e) {
                    String errorMessage = "! not an integer !";
                    if (inScriptMode()) {
                        throw new InvalidScriptException(errorMessage);
                    } else {
                        System.out.println(errorMessage);
                    }
                }
            }
            case "filter_starts_with_name" -> {
                if (args.length != 1)
                    throw new WrongNumberOfArgumentsException();
                String name = args[0];
                BandArgumentChecker.checkName(name);
                HashMap<Integer, Band> filteredBand = invoker.executeAndReturn(new FilterStartsWithName(this, receiver, name));
                if (filteredBand == null)
                    System.out.println("*no elements start with " + name + "*");
                else PrettyPrinter.printBandHashMap(filteredBand);
            }
            default -> throw new InvalidCommandException(commandName);
        }
    }

     @Override
    public void help() {
        System.out.println("*list of commands*");
        System.out.printf("%-37s", "- help");
        System.out.println(" : вывести справку по доступным командам");
        System.out.printf("%-37s", "- info");
        System.out.println(" : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        System.out.printf("%-37s", "- show");
        System.out.println(" : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.printf("%-37s", "- insert null {element}");
        System.out.println(" : добавить новый элемент с заданным ключом");
        System.out.printf("%-37s", "- update id {element}");
        System.out.println(" : обновить значение элемента коллекции, id которого равен заданному");
        System.out.printf("%-37s", "- remove_key null");
        System.out.println(" : удалить элемент из коллекции по его ключу");
        System.out.printf("%-37s", "- clear");
        System.out.println(" : очистить коллекцию");
        System.out.printf("%-37s", "- save");
        System.out.println(" : сохранить коллекцию в файл");
        System.out.printf("%-37s", "- execute_script file_name");
        System.out.println(" : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.printf("%-37s", "- exit");
        System.out.println(" : завершить программу (без сохранения в файл)");
        System.out.printf("%-37s", "- replace_if_greater null {element}");
        System.out.println(" : заменить значение по ключу, если новое значение больше старого");
        System.out.printf("%-37s", "- remove_lower_key null");
        System.out.println(" : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        System.out.printf("%-37s", "- count_by_albums albumsCount");
        System.out.println(" : вывести количество элементов, значение поля albumsCount которых равно заданному");
        System.out.printf("%-37s", "- filter_starts_with_name name");
        System.out.println(" : вывести элементы, значение поля name которых начинается с заданной подстроки");
    }

    @Override
    public void exit() {
        canExit = true;
    }

    @Override
    public void history() {
        Stack<AbstractCommand> commandHistory = invoker.getCommandHistory();
        System.out.println("*command history*");
        for (AbstractCommand command : commandHistory) {
            System.out.println(command);
        }
    }

    @Override
    public void executeScript(String path) throws CustomIOException {
        try {
            if (pathStackContains(path))
                throw new FileRecursionError(path);

            BasicReader basicReader = new CustomFileReader(path);
            pathStack.push(path);
            int lineCounter = 0;
            while (basicReader.hasNextLine()) {
                try {
                    lineCounter += 1;
                    readAndExecuteCommand(basicReader);
                } catch (InvalidCommandException | CollectionKeyException | WrongNumberOfArgumentsException |
                         WrongArgumentException | InvalidScriptException e) {
                    System.out.println(printPathStack() + ":" + lineCounter + ": " + e.getMessage());
                }
            }
            pathStack.pop();
        } catch (FileRecursionError | FileNotFoundException | FilePermissionException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean pathStackContains(String pathToCheck) throws CustomIOException {
        try {
            for (String pathFromStack : pathStack) {
                Path path1 = Paths.get(pathFromStack);
                Path path2 = Paths.get(pathToCheck);
                if (Files.isSameFile(path1, path2))
                    return true;
            }
            return false;
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    private String printPathStack() {
        StringBuilder returnString = new StringBuilder();
        for (String path : pathStack) {
            returnString.append(path).append(":");
        }
        returnString.deleteCharAt(returnString.length() - 1);
        return returnString.toString();
    }
    private boolean inScriptMode() {
        return !pathStack.empty();
    }
}