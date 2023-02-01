package tui;

import model.Member;
import model.MemberCont;

public class Main {
	public static void main(String[] args) {
		writeTestData();
		Tui tui = new Tui();
		tui.start();
		System.out.println("Clap the horses");
	}

	private static void writeTestData() {
		MemberCont mc = MemberCont.getInstance();
		mc.addMember(new Member("Joe", "joe@", "12121212"));
		mc.addMember(new Member("Jane", "jane@", "77777777"));
		mc.addMember(new Member("Janice", "janice@", "12345678"));
	}
}
