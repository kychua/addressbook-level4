package seedu.address.commons.core;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * A Serializable class that contains the Parser settings.
 */
public class ParserSettings implements Serializable {

    private static final HashMap<String, String> DEFAULT_COMMAND_ALIASES;
    private final HashMap<String, String> commandAliases;

    static {
        // TODO: refactor to reduce repetition
        DEFAULT_COMMAND_ALIASES = new HashMap<String, String>();
        DEFAULT_COMMAND_ALIASES.put("a", "add");
        DEFAULT_COMMAND_ALIASES.put("c", "clear");
        DEFAULT_COMMAND_ALIASES.put("clr", "clear");
        DEFAULT_COMMAND_ALIASES.put("reset", "clear");
        DEFAULT_COMMAND_ALIASES.put("d", "delete");
        DEFAULT_COMMAND_ALIASES.put("del", "delete");
        DEFAULT_COMMAND_ALIASES.put("dlt", "delete");
        DEFAULT_COMMAND_ALIASES.put("remove", "delete");
        DEFAULT_COMMAND_ALIASES.put("e", "exit");
        DEFAULT_COMMAND_ALIASES.put("q", "exit");
        DEFAULT_COMMAND_ALIASES.put("quit", "exit");
        DEFAULT_COMMAND_ALIASES.put("f", "find");
        DEFAULT_COMMAND_ALIASES.put("get", "find");
        DEFAULT_COMMAND_ALIASES.put("search", "find");
        DEFAULT_COMMAND_ALIASES.put("h", "help");
        DEFAULT_COMMAND_ALIASES.put("sos", "find");
        DEFAULT_COMMAND_ALIASES.put("l", "list");
        DEFAULT_COMMAND_ALIASES.put("s", "select");
        DEFAULT_COMMAND_ALIASES.put("sel", "select");
    }

    public ParserSettings() {
        this.commandAliases = DEFAULT_COMMAND_ALIASES;
    }

    public ParserSettings(HashMap<String, String> commandAliases) {
        this.commandAliases = commandAliases;
    }

    public HashMap<String, String> getCommandAliases() {
        return commandAliases;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this){
            return true;
        }
        if (!(other instanceof ParserSettings)){ //this handles null as well.
            return false;
        }

        ParserSettings o = (ParserSettings) other;

        return Objects.equals(commandAliases, o.commandAliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandAliases);
    }

    @Override
    public String toString(){
        return commandAliases.toString();
    }
}
