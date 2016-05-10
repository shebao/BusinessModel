import java.util.EnumSet;

import com.shebao.model.Employee.EmpType;

public class Test{

	public static void main(String[] args) {
		EmpType a = EmpType.General;
		EnumSet<EmpType> es = EnumSet.allOf(EmpType.class);
		for (EmpType empType : es) {

			System.out.println(empType.ordinal());
		}
	}
}
