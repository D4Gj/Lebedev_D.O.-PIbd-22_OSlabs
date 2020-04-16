public class Page {
	private String data = "";
	private int age;

	public Page(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public int getAge() {
		return age;
	}

	public void grow() {
		age++;
	}

	public void use() {
		age = 0;
	}
}
