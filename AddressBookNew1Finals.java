public interface AddressBookNew1Finals {
	final static int NUMBER_OF_OBJECTS_REGULAR_PANES = 2;
	final static int MAX_NUM_OF_EDIT_PANES = 1;
	final static int MAX_NUM_OF_PANES  = NUMBER_OF_OBJECTS_REGULAR_PANES+MAX_NUM_OF_EDIT_PANES;
	final static String NUMBER_OF_OBJECTS_LE0 = "NUMBER OF OBJECTS <= 0";
	final static String SINGLETON_MESSAGE = "Singelton violation.Only " + MAX_NUM_OF_PANES + " panes can created.\n";
	final static String TITLE = "AddressBookNew";
	final static String STYLES_CSS = "styles.css";
	final static String FILE_NAME = "address.dat";
	final static String FILE_MODE = "rw";
	final static String ADD = "Add";
	final static String REDO = "Redo";
	final static String UNDO = "Undo";
	final static String FIRST = "First";
	final static String NEXT = "Next";
	final static String PREVIOUS = "Previous";
	final static String LAST = "Last";
	final static String CLEAR = "Clear";
	final static String REVERSE = "Reverse";
	final static String ZIP = "Zip";
	final static String NAME = "Name";
	final static String STREET = "Street";
	final static String CITY = "City";
	final static String STATE = "State";
	final static int NAME_SIZE = 32;
	final static int STREET_SIZE = 32;
	final static int CITY_SIZE = 20;
	final static int STATE_SIZE = 2;
	final static int ZIP_SIZE = 5;
	final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
	final static int RECORD_SIZE_IN_BYTES = 2 * RECORD_SIZE;
	final static String STYLE_COMMAND = "-fx-border-color: grey;" + " -fx-border-width: 1;"
			+ " -fx-border-style: solid outside ;";
}
