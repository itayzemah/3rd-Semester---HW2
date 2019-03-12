import javafx.scene.control.Button;

public class EditableAddressBookPane extends AddressBookDecorator {
	private AddButton jbtAdd;
	private UndoButton jbtUndo;
	private	RedoButton jbtRedo;
	Originator originator;
	CareTaker careTaker;
	AddressBookPane addressBook;
	public EditableAddressBookPane(AddressBookPane addressBook) {
		super(addressBook);
		originator = new Originator();
		careTaker = new CareTaker();
		
			

		jbtAdd = new AddButton(addressBook, addressBook.getRaf());
		addButton(jbtAdd);
		
		jbtUndo = new UndoButton(addressBook, addressBook.getRaf(),careTaker,originator);
		addButton(jbtUndo);
		
		jbtRedo = new RedoButton(addressBook, addressBook.getRaf(),careTaker,originator);
		addButton(jbtRedo);
	}

	private void addButton(Button btn) {
		btn.setOnAction(e -> ((Command) e.getSource()).Execute());
		if (eventType.ADD.getDoEvent())
		getAddressBookPane().getButtonPanel().getChildren().add(btn);
	}



	
	

}
