public class BankAccount {

	private static final String ACCOUNT_CLOSED = "Account closed";
	private static final String CANNOT_USE_NEGATIVE_AMOUNT = "Cannot deposit or withdraw negative amount";
	private static final String CANNOT_WITHDRAW_FROM_EMPTY_ACCOUNT = "Cannot withdraw money from an empty account";
	private static final String CANNOT_WITHDRAW_MORE_THAN_BALANCE = "Cannot withdraw more money than is currently in the account";

	private boolean active = false;
	private int balance = 0;

	public void open() {
		active = true;
	}

	public int getBalance() throws BankAccountActionInvalidException {
		if (!active) {
			throw new BankAccountActionInvalidException(ACCOUNT_CLOSED);
		}

		synchronized (this) {
			return balance;
		}
	}

	public void deposit(int amount) throws BankAccountActionInvalidException {
		if (!active) {
			throw new BankAccountActionInvalidException(ACCOUNT_CLOSED);
		}

		synchronized (this) {
			if (amount < 0) {
				throw new BankAccountActionInvalidException(CANNOT_USE_NEGATIVE_AMOUNT);
			}
			balance += amount;
		}
	}

	public void withdraw(int amount) throws BankAccountActionInvalidException {
		if (!active) {
			throw new BankAccountActionInvalidException(ACCOUNT_CLOSED);
		}

		if (amount < 0) {
			throw new BankAccountActionInvalidException(CANNOT_USE_NEGATIVE_AMOUNT);
		}

		synchronized (this) {
			if (balance <= 0) {
				throw new BankAccountActionInvalidException(CANNOT_WITHDRAW_FROM_EMPTY_ACCOUNT);
			}

			if (balance - amount < 0) {
				throw new BankAccountActionInvalidException(CANNOT_WITHDRAW_MORE_THAN_BALANCE);
			}

			balance -= amount;
		}
	}

	public void close() {
		active = false;
	}
}
