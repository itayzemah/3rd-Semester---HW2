public abstract class AddressBookDecorator implements AddressBookInter, AddressBookNew1Finals {

	AddressBookPane addressBook;

	public AddressBookDecorator(AddressBookPane addressBook) {
			this.addressBook = addressBook;
	}

	public AddressBookPane getAddressBookPane() {
		return addressBook;
	}

}
