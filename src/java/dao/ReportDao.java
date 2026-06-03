package dao;

import java.util.List;
import model.OverdueReport;

public interface ReportDao {

    List<OverdueReport> getMemberOverdueReport(int memberId);

}