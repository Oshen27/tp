package seedu.command;

import java.util.ArrayList;
import java.util.List;

public class TestCommand extends Command {
    public static final String COMMAND_WORD = "test";
    public static final String COMMAND_GUIDE = "A command for testing.";
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {"arg1/"};
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"arg2/"};

    /**
     * Executes the test command and returns a list of messages.
     *
     * @return A list of strings containing the messages generated by the command execution.
     */
    @Override
    public List<String> execute() {
        List<String> messages = new ArrayList<>();
        messages.add("Test command executed.");
        return messages;
    }

    /**
     * Gets the mandatory keywords for the command.
     *
     * @return An array of strings containing the mandatory keywords associated with this command.
     */
    @Override
    protected String[] getMandatoryKeywords() {
        return COMMAND_MANDATORY_KEYWORDS;
    }

    /**
     * Gets the extra keywords for the command.
     *
     * @return An array of strings containing the extra keywords associated with this command.
     */
    @Override
    protected String[] getExtraKeywords() {
        return COMMAND_EXTRA_KEYWORDS;
    }
}
