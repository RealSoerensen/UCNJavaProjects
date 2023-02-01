package tui;

import java.util.List;
import java.util.Scanner;

import ctrl.MemberCtrl;
import ctrl.RegisterDogCtrl;
import model.Dog;
import model.Member;

public class Tui {
	private RegisterDogCtrl rdc;
	private Scanner scanner;
	private MemberCtrl memCtrl;

	public Tui() {
		scanner = new Scanner(System.in);
		memCtrl = new MemberCtrl();
	}

	public void start() {
		System.out.println("\nWelcome to the FFC Management System");
		boolean goon = true;
		while (goon) {
			System.out.print("Pick one: register dog (d), show all (a), create (c), read (r), exit (x)");
			String input = scanner.nextLine().toLowerCase();
			switch (input) {
			case "d":
				registerDog();
				break;
			case "c":
				createMember();
				break;
			case "r":
				readMember();
				break;
			case "a":
				showAllMembers();
				break;
			case "x":
				exit();
				goon = false;
				break;
			default:
				System.out.println("I don't understand " + input + " try again!");
			}
		}
	}

	private void showAllMembers() {
		List<Member> members = new MemberCtrl().findAll();
		displayMembers(members);
	}

	private void displayMembers(List<Member> members) {
		System.out.println("Members:");
		for(Member member : members) {
			System.out.printf("\t%s\t%s\t%s\tNo. of dogs:%d\towes:%s\n", member.getName(), member.getEmail(), member.getPhone(), member.getDogs().size(), (member.isFeeDue(2022)?"yes":"no"));
		}
	}

	private void registerDog() {
		rdc = new RegisterDogCtrl();
		if (readMemberPartOfRegisterDog() != null) {
			boolean goon = true;
			while (goon) {
				System.out.print("\n  Register a dog? (y/n)");
				String sGoon = scanner.nextLine();
				if (sGoon.equalsIgnoreCase("n")) {
					goon = false;
				} else {
					System.out.print("    Dog's name: ");
					String dogName = scanner.nextLine();
					System.out.print("    Fee year: ");
					String feeYear = scanner.nextLine();
					int iFeeYear = Integer.parseInt(feeYear);
					Dog dog = rdc.registerDog(dogName, iFeeYear);
					displayDog(dog);
				}
			}
		}
	}

	private void displayDog(Dog dog) {
		System.out.println("  Dog:");
		System.out.println("    Name: " + dog.getName());
		System.out.println("    Fee year: " + dog.getFeeYear());
		System.out.println("    Fee" + (dog.isFeeDue(2022) ? " is " : " is not ") + "due");
	}

	private void readMember() {
		System.out.println("Read member:");
		System.out.print("  User email to search for: ");
		String email = scanner.nextLine();
		Member m = memCtrl.findByEmail(email);
		displayMember("Member", m);
	}

	private Member readMemberPartOfRegisterDog() {
		System.out.println("Read member:");
		System.out.print("  User email to search for: ");
		String email = scanner.nextLine();
		Member m = rdc.findByEmail(email);
		displayMember("Member", m);
		return m;
	}

	private void displayMember(String title, Member m) {
		System.out.println("  " + title);
		if (m == null) {
			System.out.println("    Not found");
		} else {
			System.out.println("    Name: " + m.getName());
			System.out.println("    Email: " + m.getEmail());
			System.out.println("    Phone number: " + m.getPhone());
			System.out.println("This member's dogs: ");
			for(Dog dog : m.getDogs()) {
				displayDog(dog);
			}
		}
		
	}

	private void createMember() {
		System.out.println("  Register member");// Create Member
		System.out.print("    Name: ");
		String name = scanner.nextLine();
		System.out.print("    Email: ");
		String email = scanner.nextLine();
		System.out.print("    Phone number: ");
		String phone = scanner.nextLine();
		Member m = memCtrl.createMember(name, email, phone);
		displayMember("Member created", m);
	}

	private void exit() {
		System.out.println("Thank you for using FFC Management System, good bye...");
	}

}
