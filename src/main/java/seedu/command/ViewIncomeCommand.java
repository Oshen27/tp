package seedu.command;

import seedu.transaction.Income;
import seedu.transaction.Transaction;
import seedu.transaction.TransactionList;
import seedu.utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewIncomeCommand extends Command {
    public static final String COMMAND_WORD = "view-income"; // The word associated with the command
    public static final String COMMAND_GUIDE = "view-income [f/ START_DATE] [t/ END_DATE]: "
            + "View your income history"; // A guide or description of the command
    public static final String[] COMMAND_MANDATORY_KEYWORDS = {}; // Keywords for arguments
    public static final String[] COMMAND_EXTRA_KEYWORDS = {"f/", "t/"}; // Keywords for arguments

    public static final String INCOME_EMPTY_MESSAGE = "No income to show!"; // Keywords for arguments

    private TransactionList transactionList = new TransactionList();

    /**
     * Constructor sets the transaction list for the command.
     *
     * @param transactionList the input transaction list
     */
    public ViewIncomeCommand(TransactionList transactionList) {
        setTransactionList(transactionList);
    }

    /**
     * Sets the transaction list for the command.
     *
     * @param transactionList the input transaction list
     */
    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Executes the command and returns a list of income information.
     *
     * @return A list of strings containing the messages generated by the command execution.
     */
    @Override
    public List<String> execute() {
        List<String> messages = new ArrayList<>();

        String startDate = arguments.get(COMMAND_EXTRA_KEYWORDS[0]);
        String endDate = arguments.get(COMMAND_EXTRA_KEYWORDS[1]);

        List<Transaction> temp;

        temp = transactionList.getTransactions().stream()
                .filter(transaction -> transaction instanceof Income)
                .collect(Collectors.toList());

        if (startDate != null) {
            try {
                LocalDateTime start = DateTimeUtils.parseDateTime(startDate);
                temp = temp.stream()
                        .filter((t) -> t.getDate().isAfter(start) || t.getDate().isEqual(start))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                messages.add(e.getMessage());
                return messages;
            }
        }
        if (endDate != null) {
            try {
                LocalDateTime end = DateTimeUtils.parseDateTime(endDate);
                temp = temp.stream()
                        .filter((t) -> t.getDate().isBefore(end) || t.getDate().isEqual(end))
                        .collect(Collectors.toList());
            } catch (Exception e) {
                messages.add(e.getMessage());
                return messages;
            }
        }

        if (temp.isEmpty()) {
            messages.add(INCOME_EMPTY_MESSAGE);
            return messages;
        }

        int i = 1;
        for (Transaction transaction: temp) {
            messages.add(i + ". " + transaction.toString());
            i++;
        }

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

    /**
     * Gets the word for the command.
     *
     * @return A string representing the command word.
     */
    @Override
    protected String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Gets the guide for the command.
     *
     * @return A string representing the command guide.
     */
    @Override
    protected String getCommandGuide() {
        return COMMAND_GUIDE;
    }
}

