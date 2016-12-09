package seedu.address.commons.core;

import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

/**
 * A Serializable class that contains the Parser settings.
 */
public class ParserSettings implements Serializable {

    private static final HashMap<String, String> DEFAULT_ALIAS_TO_COMMAND;
    private static final KeyValuesPair[] COMMANDS_TO_ALIASES = {
        new KeyValuesPair("add", new String[] {"a"}),
        new KeyValuesPair("clear", new String[] {"c", "clr", "reset"}),
        new KeyValuesPair("delete", new String[] {"d", "del", "remove"}),
        new KeyValuesPair("exit", new String[] {"quit", "q"}),
        new KeyValuesPair("find", new String[] {"f", "get", "search"}),
        new KeyValuesPair("help", new String[] {"h", "sos"}),
        new KeyValuesPair("list", new String[] {"l"}),
        new KeyValuesPair("select", new String[] {"s", "sel", "show"})
    };

    private final HashMap<String, String> aliasToCommand;

    static {
        DEFAULT_ALIAS_TO_COMMAND = new HashMap<String, String>();
        addDefaultCommandAliases(DEFAULT_ALIAS_TO_COMMAND);
        Collections.unmodifiableMap(DEFAULT_ALIAS_TO_COMMAND);
    }

    /**
     * Adds default command aliases to {@code aliasToCommand}, used for setup of {@code DEFAULT_ALIAS_TO_COMMAND}.
     */
    private static void addDefaultCommandAliases(HashMap<String, String> aliasToCommand) {
        for (KeyValuesPair commandToAliases : COMMANDS_TO_ALIASES) {
            String command = commandToAliases.key;
            String[] aliases = commandToAliases.values;
            for (String alias : aliases) {
                aliasToCommand.put(alias, command);
            }
        }
    }

    public ParserSettings() {
        this.aliasToCommand = DEFAULT_ALIAS_TO_COMMAND;
    }

    public ParserSettings(HashMap<String, String> commandAliases) {
        this.aliasToCommand = commandAliases;
    }

    public HashMap<String, String> getCommandAliases() {
        return aliasToCommand;
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

        return Objects.equals(aliasToCommand, o.aliasToCommand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliasToCommand);
    }

    @Override
    public String toString(){
        return aliasToCommand.toString();
    }

    /**
     * Helper class that stores a key and an array of values.
     */
    static class KeyValuesPair {
        public String[] values;
        public String key;
        public KeyValuesPair(String key, String[] values) {
            this.key = key;
            this.values = values;
        }
    }
}
