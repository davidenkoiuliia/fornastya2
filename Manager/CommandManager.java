package Manager;
import Exceptions.CommandInterruptException;
import Exceptions.UnknownCommandException;
import Exceptions.WrongAmountOfArgumentsException;
import Manager.AllCommands.*;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;

public class CommandManager {
    LinkedHashMap<String, Command> commands;
    public CommandManager(){
        commands = new LinkedHashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("remove_by_key", new RemoveCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("max_by_studio", new MaxByStudioCommand());
        commands.put("count_by_albums_count", new CountByAlbumsCommand());
        commands.put("filter_starts_with_name", new FiltersStartsWithCommand());
        commands.put("insert", new InsertCommand());
        commands.put("update", new UpdateCommand());
        commands.put("history", new HistoryCommand());
        commands.put("replace_if_greater", new ReplaceIfGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());
    }
    public CommandManager(CommandMode mode, Scanner scanner) {
        commands = new LinkedHashMap<>();
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand());
        commands.put("show", new ShowCommand());
        commands.put("remove_by_key", new RemoveCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("history", new HistoryCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("exit", new ExitCommand());
        commands.put("max_by_studio", new MaxByStudioCommand());
        commands.put("count_by_albums_count", new CountByAlbumsCommand());
        commands.put("filter_starts_with_name", new FiltersStartsWithCommand());

        /* скоро тут будет код чтобы сделать более крутой конструктор

        commands.put("insert", new InsertCommand());
        commands.put("update", new UpdateCommand());
        commands.put("replace_if_greater", new ReplaceIfGreaterCommand());
        commands.put("remove_lower", new RemoveLowerCommand());

        */
    }
    public LinkedHashMap<String, Command> getCommands() {
        return commands;
    }
    public void executeCommand(String[] args) throws CommandInterruptException{
        try {
            Optional.ofNullable(commands.get(args[0])).orElseThrow(() -> new UnknownCommandException("Указанная команда не была обнаружена")).execute(args);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
            throw new CommandInterruptException(e);
        } catch (UnknownCommandException e) {
            System.out.println(e.getMessage());
            throw new CommandInterruptException(e);
        } catch (WrongAmountOfArgumentsException e) {
            System.out.println("Неверное количество аргументов. Попробуйте ещё раз ");
            throw new CommandInterruptException(e);
        } catch (Exception e) {
            System.out.println("В командном менеджере произошла непредвиденная ошибка " + e.getMessage());
            throw new CommandInterruptException(e);
        }
    }
}
