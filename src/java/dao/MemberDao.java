package dao;

import java.util.List;
import model.Member;

public interface MemberDao {

    void addMember(Member member);

    void updateMember(Member member);

    void deleteMember(int memberId);

    Member getMemberById(int memberId);

    List<Member> getAllMembers();
}
