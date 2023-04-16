package Manager.AllCommands;
import Manager.CommandManager;

import java.util.Optional;

public class HelpCommand implements Command {
    @Override
    public String getName() {
        return "help";
    }
    @Override
    public String getDescr() {
        return "Выводит справку о всех доступных командах";
    }
    @Override
    public void execute(String[] args) {
        CommandManager manager = new CommandManager();
            if (args.length == 1)
            {
                manager.getCommands().forEach((name,command) -> System.out.println(name + " " + command.getArgs() + " --  " + command.getDescr()));
            }
            else
            {
                for (int i = 1; i < args.length; i++)
                {
                    var command = manager.getCommands().get(args[i]);
                    System.out.println(args[i] + " -- " + Optional.ofNullable(command).map(Command::getDescr).orElse("Команда не найдена"));
                }
            }
    }
}
