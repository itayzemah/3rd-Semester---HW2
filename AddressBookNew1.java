import java.io.IOException;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddressBookNew1 extends Application implements AddressBookNew1Finals {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Stage[] stages = new Stage[MAX_NUM_OF_PANES + 1];
		Scene[] scenes = new Scene[MAX_NUM_OF_PANES + 1];
		AddressBookPane[] panes = new AddressBookPane[MAX_NUM_OF_PANES + 1];

		for (int i = 0; i < 1 + MAX_NUM_OF_PANES; i++) {
			try {
				panes[i] = AddressBookPane.getInstance();
				scenes[i] = new Scene(panes[i]);
				stages[i] = new Stage();
				stages[i].setTitle(TITLE);
				stages[i].setScene(scenes[i]);
				stages[i].setResizable(true);
				stages[i].show();
				stages[i].setAlwaysOnTop(true);
				stages[i].setOnCloseRequest(event -> {
				});
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
			}
		}

	}

}

class AddressBookPane extends GridPane implements AddressBookNew1Finals, AddressBookInter {
	private RandomAccessFile raf;
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();
	private FlowPane jpButton;
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	private ClearButton jbtClear;
	private ReverseButton jbtReverse;
	private EventHandler<ActionEvent> ae = e -> ((Command) e.getSource()).Execute();
	private static int numOfPanes;

	private AddressBookPane() {
		try {
			raf = new RandomAccessFile(FILE_NAME, FILE_MODE);
		} catch (IOException ex) {
			System.out.println(ex);
			System.exit(0);
		}
		numOfPanes++;
		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(25);
		jtfZip.setPrefWidth(60);
		jbtFirst = new FirstButton(this, raf);
		jbtNext = new NextButton(this, raf);
		jbtPrevious = new PreviousButton(this, raf);
		jbtLast = new LastButton(this, raf);
		jbtClear = new ClearButton(this, raf);
		jbtReverse = new ReverseButton(this, raf);
		Label state = new Label(STATE);
		Label zp = new Label(ZIP);
		Label name = new Label(NAME);
		Label street = new Label(STREET);
		Label city = new Label(CITY);
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);
		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		jpAddress.setStyle(STYLE_COMMAND);
		jpButton = new FlowPane();
		jpButton.setHgap(5);
		if (eventType.FIRST.getDoEvent())
			jpButton.getChildren().add(jbtFirst);
		if (eventType.NEXT.getDoEvent())
			jpButton.getChildren().add(jbtNext);
		if (eventType.PREVIOUS.getDoEvent())
			jpButton.getChildren().add(jbtPrevious);
		if (eventType.LAST.getDoEvent())
			jpButton.getChildren().add(jbtLast);

		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		this.setPrefWidth(500);
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);

		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbtClear.setOnAction(ae);
		jbtReverse.setOnAction(ae);
		jbtFirst.Execute();
	}

	protected RandomAccessFile getRaf() {
		return raf;
	}

	protected FlowPane getButtonPanel() {
		return jpButton;
	}

	public void SetName(String text) {
		jtfName.setText(text);
	}

	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}

	public void SetCity(String text) {
		jtfCity.setText(text);
	}

	public void SetState(String text) {
		jtfState.setText(text);
	}

	public void SetZip(String text) {
		jtfZip.setText(text);
	}

	public String GetName() {
		return jtfName.getText();
	}

	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String GetCity() {
		return jtfCity.getText();
	}

	public String GetState() {
		return jtfState.getText();
	}

	public String GetZip() {
		return jtfZip.getText();
	}

	public static int getNumOfBasePanes() {
		return numOfPanes;
	}

	public static void setNumOfPanes(int numOfPanes) {
		AddressBookPane.numOfPanes = numOfPanes;
	}

	public void clearTextFields() {
		jtfName.setText("");
		jtfStreet.setText("");
		jtfCity.setText("");
		jtfState.setText("");
		jtfZip.setText("");
	}

	public static AddressBookPane getInstance() throws IllegalAccessException {
		if (numOfPanes < MAX_NUM_OF_EDIT_PANES) {
			return new EditableAddressBookPane(new AddressBookPane()).getAddressBookPane();
		}
		if (numOfPanes > MAX_NUM_OF_PANES) {
			throw new IllegalAccessException(SINGLETON_MESSAGE);
		} else {
			numOfPanes++;
			return new AddressBookPane();
		}
	}

	@Override
	public AddressBookPane getAddressBookPane() {
		return this;
	}

}

interface Command {
	public void Execute();
}

class CommandButton extends Button implements Command, AddressBookNew1Finals {
	private AddressBookPane p;
	private RandomAccessFile raf;

	public CommandButton(AddressBookPane pane, RandomAccessFile r) {
		super();
		p = pane;
		raf = r;

	}

	public AddressBookPane getPane() {
		return p;
	}

	public RandomAccessFile getFile() {
		return raf;
	}

	public void setPane(AddressBookPane p) {
		this.p = p;
	}

	@Override
	public void Execute() {
	}

	public void writeAddress(long position) {
		try {
			getFile().seek(position);
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetName(), NAME_SIZE, getFile());
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetStreet(), STREET_SIZE, getFile());
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetCity(), CITY_SIZE, getFile());
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetState(), STATE_SIZE, getFile());
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetZip(), ZIP_SIZE, getFile());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readAddress(long position) throws IOException {
		getFile().seek(position);
		String name = FixedLengthStringIO1.readFixedLengthString(NAME_SIZE, getFile());
		String street = FixedLengthStringIO1.readFixedLengthString(STREET_SIZE, getFile());
		String city = FixedLengthStringIO1.readFixedLengthString(CITY_SIZE, getFile());
		String state = FixedLengthStringIO1.readFixedLengthString(STATE_SIZE, getFile());
		String zip = FixedLengthStringIO1.readFixedLengthString(ZIP_SIZE, getFile());
		getPane().SetName(name);
		getPane().SetStreet(street);
		getPane().SetCity(city);
		getPane().SetState(state);
		getPane().SetZip(zip);
	}
	
}

class AddButton extends CommandButton {
	private static int numOfclicks = 0;

	public AddButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(ADD);
	}

	@Override
	public void Execute() {
		try {
			writeAddress(getFile().length());
			numOfclicks++;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int getNumOfclicks() {
		return numOfclicks;
	}
	protected static void reduceNumOfClicks() {
		numOfclicks--;
	}
	

}

class UndoButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public UndoButton(AddressBookPane pane, RandomAccessFile r, CareTaker careTaker, Originator originator) {
		super(pane, r);
		this.setText(UNDO);
		this.careTaker = careTaker;
		this.originator = originator;
	}

	@Override
	public void Execute() {
		if (AddButton.getNumOfclicks() > 0) {
			try {
				if (getFile().length() != 0) {
					originator.setState(getLiveRecord());
					careTaker.add(originator.saveStateToMemento());
					getFile().setLength(getFile().length() - RECORD_SIZE_IN_BYTES);
					AddButton.reduceNumOfClicks();
					new FirstButton(getPane(), getFile()).Execute();
				} else {
					getPane().clearTextFields();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private String getLiveRecord() throws IOException {
		getFile().seek(getFile().length() - (RECORD_SIZE_IN_BYTES));
		return FixedLengthStringIO1.readFixedLengthString(RECORD_SIZE, getFile());
	}
}

class RedoButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public RedoButton(AddressBookPane pane, RandomAccessFile r, CareTaker careTaker, Originator originator) {
		super(pane, r);
		this.setText(REDO);
		this.careTaker = careTaker;
		this.originator = originator;
	}

	@Override
	public void Execute() {
		if (!careTaker.isMementoEmpy()) {
			try {
				originator.getStateFromMemento(careTaker.getPrev());
				getFile().seek(getFile().length());
				FixedLengthStringIO1.writeFixedLengthString(originator.getRecord(), RECORD_SIZE, getFile());
				new FirstButton(getPane(), getFile()).Execute();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class NextButton extends CommandButton {
	public NextButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(NEXT);
	}

	@Override
	public void Execute() {
		try {
			long currentPosition = getFile().getFilePointer();
			if (currentPosition < getFile().length())
				readAddress(currentPosition);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class PreviousButton extends CommandButton {
	public PreviousButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(PREVIOUS);
	}

	@Override
	public void Execute() {
		try {
			long currentPosition = getFile().getFilePointer();
			if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
				readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
			else
				;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class LastButton extends CommandButton {
	public LastButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(LAST);
	}

	@Override
	public void Execute() {
		try {
			long lastPosition = getFile().length();
			if (lastPosition > 0)
				readAddress(lastPosition - 2 * RECORD_SIZE);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class FirstButton extends CommandButton {
	public FirstButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(FIRST);
	}

	@Override
	public void Execute() {
		try {
			if (getFile().length() > 0)
				readAddress(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class ClearButton extends CommandButton {
	public ClearButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(CLEAR);
	}

	@Override
	public void Execute() {
		try {
			getFile().setLength(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		getPane().clearTextFields();
	}
}

class ReverseButton extends CommandButton {
	public ReverseButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(REVERSE);
	}

	@Override
	public void Execute() {
		try {
			long originalRafLength = getFile().length();
			long numberOfRecords = (getFile().length()) / (2 * RECORD_SIZE);
			if (numberOfRecords <= 1)
				return;
			for (int i = 0; i < numberOfRecords / 2; i++) {
				readAddress(i * 2 * RECORD_SIZE);
				writeAddress(originalRafLength);
				readAddress(originalRafLength - (i + 1) * 2 * RECORD_SIZE);
				writeAddress(i * 2 * RECORD_SIZE);
				readAddress(originalRafLength);
				writeAddress(originalRafLength - (i + 1) * 2 * RECORD_SIZE);
				getFile().setLength(originalRafLength);
			}
			readAddress(0);
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
