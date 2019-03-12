
public interface AddressBookInter {
	AddressBookPane getAddressBookPane();
	enum eventType {
		FIRST(true), LAST(true), NEXT(true), PREVIOUS(true), ADD(true), REDO(true), UNDO(true);
		private boolean doEvent;

		eventType(boolean doEvent) {
			this.doEvent = doEvent;
		}

		boolean getDoEvent() {
			return doEvent;
		}
	}
}
