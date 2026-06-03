package console;

import dao.MemberDao;
import daoimpl.MemberDaoImpl;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import model.Member;
import model.StudentMember;
import model.FacultyMember;

public class MemberHandler {

    private static Scanner sc = new Scanner(System.in);
    private static MemberDao memberDao = new MemberDaoImpl();

    public static void memberMenu() {

        while (true) {

            System.out.println("\n===== MEMBER MENU =====");
            System.out.println("1. Add Member");
            System.out.println("2. View All Members");
            System.out.println("3. Search Member By ID");
            System.out.println("4. Update Member");
            System.out.println("5. Delete Member");
            System.out.println("6. Back");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addMember();
                    break;

                case 2:
                    viewAllMembers();
                    break;

                case 3:
                    searchMemberById();
                    break;

                case 4:
                    updateMember();
                    break;

                case 5:
                    deleteMember();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    private static void addMember() {

        try {

            System.out.println("1. Student");
            System.out.println("2. Faculty");
            System.out.print("Select Member Type: ");

            int type = sc.nextInt();
            sc.nextLine();

            Member member;

            if (type == 1) {
                member = new StudentMember();
                member.setMemberType("STUDENT");
            } else {
                member = new FacultyMember();
                member.setMemberType("FACULTY");
            }

            System.out.print("Enter Name: ");
            member.setMemberName(sc.nextLine());

            System.out.print("Enter Email: ");
            member.setEmail(sc.nextLine());

            System.out.print("Enter Phone: ");
            member.setPhone(sc.nextLine());

            member.setRegistrationDate(
                    new Date(System.currentTimeMillis()));

            memberDao.addMember(member);

            System.out.println("Member Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateMember() {

        try {

            System.out.print("Enter Member ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            Member member = memberDao.getMemberById(id);

            if (member == null) {
                System.out.println("Member Not Found");
                return;
            }

            System.out.print("Enter New Name: ");
            member.setMemberName(sc.nextLine());

            System.out.print("Enter New Email: ");
            member.setEmail(sc.nextLine());

            System.out.print("Enter New Phone: ");
            member.setPhone(sc.nextLine());

            memberDao.updateMember(member);

            System.out.println("Member Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteMember() {

        try {

            System.out.print("Enter Member ID: ");
            int id = sc.nextInt();

            memberDao.deleteMember(id);

            System.out.println("Member Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchMemberById() {

        try {

            System.out.print("Enter Member ID: ");
            int id = sc.nextInt();

            Member member = memberDao.getMemberById(id);

            if (member != null) {

                System.out.println("\n----- Member Details -----");
                System.out.println("ID : " + member.getMemberId());
                System.out.println("Name : " + member.getMemberName());
                System.out.println("Email : " + member.getEmail());
                System.out.println("Phone : " + member.getPhone());
                System.out.println("Type : " + member.getMemberType());
                System.out.println("Registration Date : "
                        + member.getRegistrationDate());

            } else {

                System.out.println("Member Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewAllMembers() {

        try {

            List<Member> members = memberDao.getAllMembers();

            if (members.isEmpty()) {

                System.out.println("No Members Found");
                return;
            }

            for (Member member : members) {

                System.out.println("\n---------------------");
                System.out.println("ID : " + member.getMemberId());
                System.out.println("Name : " + member.getMemberName());
                System.out.println("Email : " + member.getEmail());
                System.out.println("Phone : " + member.getPhone());
                System.out.println("Type : " + member.getMemberType());
                System.out.println("Registration Date : "
                        + member.getRegistrationDate());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
