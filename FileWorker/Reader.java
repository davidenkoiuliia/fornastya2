package FileWorker;

import java.nio.file.Path;
import java.util.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class Reader {
    private final LinkedHashMap<String[], String> result;
    private final ArrayDeque<String> values;
    private final ArrayList<String> Keys;
    public Reader()
    {
        result = new LinkedHashMap<>();
        values = new ArrayDeque<>();
        Keys = new ArrayList<>();
    }
    public LinkedHashMap<String[], String> readPath(String direction) throws IOException {
        Scanner scanner= new Scanner(Path.of(direction));

        if (!scanner.hasNextLine())
        {
            System.out.println("Wrong format");
            return result;
        }
        scanner.nextLine();
        while (scanner.hasNext())
        {
            String line = scanner.nextLine();
            line = line.trim();//уд пробелы
            if (line.isEmpty()) continue;
            String[] regexLine = line.split("<.+?>");
            this.pushValue(regexLine);
            Pattern nameStartPattern = Pattern.compile("<[^/]*?>");
            var nameStartPatternMatcher = nameStartPattern.matcher(line);
            if (nameStartPatternMatcher.find())
            {
                String nameStartTag = nameStartPatternMatcher.group();
                Keys.add(nameStartTag.substring(1, nameStartTag.length() - 1));
            }
            handleCurrentKeyAndValue();
            Pattern nameEndPattern = Pattern.compile("</\\S*?>");
            var nameEndPatternMatcher = nameEndPattern.matcher(line);
            if (nameEndPatternMatcher.find())
            {
                String nameEndTag = nameEndPatternMatcher.group();
                Keys.remove(nameEndTag.substring(2, nameEndTag.length() - 1));
            }
        }
        return result;
    }

    private void handleCurrentKeyAndValue() {
        if (!values.isEmpty())
        {
            String[] keys = new String[Keys.size()];
            int i = 0;
            for (String key : Keys)
            {
                keys[i++] = key;
            }
            result.put(keys, values.getLast());
            values.removeLast();
        }
    }
    private void pushValue(String[] regex) {
        for (String e : regex)
        {
            if (e.isEmpty()) continue;
            values.addLast(e);
        }
    }
}