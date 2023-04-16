package Manager.AllCommands;

public class InfoCommand implements Command {
    @Override
    public String getName(){
        return "info";
    }
    @Override
    public String getDescr(){
        return "Выводит информацию о коллекции";
    }
    @Override
    public void execute(String[] args) throws IllegalArgumentException{
        
    }
}
