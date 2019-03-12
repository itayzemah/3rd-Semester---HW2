import java.util.Stack;

class Memento {
	private String record;

	public Memento(String record) {
		this.record = record;
	}

	public String getRecord() {
		return record;
	}

}

class CareTaker {
	private Stack<Memento> mementoList = new Stack<Memento>();

	public boolean isMementoEmpy() {
		return this.mementoList.isEmpty();
	}

	public void add(Memento state) {
		if (state != null) {
			mementoList.push(state);
		}
	}

	public Memento getPrev() {
		if (isMementoEmpy()) {
			return null;
		}
		return mementoList.pop();
	}

}

class Originator {
	private String record;


	public String getRecord() {
		return record;
	}

	public void setState(String record) {
		this.record = record;
	}

	public Memento saveStateToMemento() {
		return new Memento(this.record);
	}

	public void getStateFromMemento(Memento memento) {
		if (memento != null) {
			this.record = memento.getRecord();
		}
	}
}