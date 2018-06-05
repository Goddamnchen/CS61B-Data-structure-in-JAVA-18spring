public class TestPlanet{
	public static void main(String[] args) {
		Planet p1 = new Planet(1, 2, 3, 4, 5, "jupiter.gif");
		Planet p2 = new Planet(4, 5, 6, 7, 8, "jupiter.gif");
		System.out.println(p1.calcForceExertedBy(p2));
	}
}