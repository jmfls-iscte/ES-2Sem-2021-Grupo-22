package metrics;

public class Example {

	public static void main(String[] args) {
		Example example = new Example();
		example.foo();
	}

	private void foo() {
		int x = 1;
		int y = 1;
		if (x == 1) {
			switch (x) {
			case 1:
				x = 1;
				break;
			case 2:
				x = 1;
				break;
			default:
				x = 1;
				break;
			}
			while (y < 3) {
				if (y < 5)
					y++;
				while (x < 4) {
					x++;
				}

				x = 0;
			}
		}
	}
}
