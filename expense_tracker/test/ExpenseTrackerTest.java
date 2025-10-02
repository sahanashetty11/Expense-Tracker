import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class ExpenseTrackerTest {

    private ExpenseTrackerView view;
    private ExpenseTrackerApp app;

    @Before
    public void setup() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Serial");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Category");
        tableModel.addColumn("Date");
        view = new ExpenseTrackerView(tableModel);
        app = new ExpenseTrackerApp();
    }

    @Test
    public void testAddTransaction() {
        // Create a new transaction
        double amount = 100.0;
        String category = "Food";
        Transaction transaction = new Transaction(amount, category);

        // Add the transaction to the view
        view.addTransaction(transaction);

        // Get the transactions from the view
        java.util.List<Transaction> transactions = view.getTransactions();

        // Verify that the transaction was added
        assertEquals(1, transactions.size());
        assertEquals(amount, transactions.get(0).getAmount(), 0.001);
        assertEquals(category, transactions.get(0).getCategory());
    }

    @Test
    public void testInValidAmountAndCategory() {
        List<String> errs = InputValidation.validateAmountAndCategory(1020, "groceries");
        assertEquals(2, errs.size());
        assertTrue(errs.get(0).equals("Amount must be a number greater than 0 and less than 1000."));
        assertTrue(errs.get(1).equals("Allowed categories are: food, travel, bills, entertainment, other."));
    }
}