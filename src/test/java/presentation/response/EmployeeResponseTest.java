package presentation.response;

import domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeResponseTest {
    @Test
    void 山田太郎というEmployeeに対してEmployeeResponseが作成できる() {
        Employee employee = new Employee("1", "Taro", "Yamada");
        EmployeeResponse actual =  EmployeeResponse.createResponse(employee);

        EmployeeResponse expected = new EmployeeResponse("1", "Taro", "Yamada");

        assertEquals(expected, actual);
    }

    @Test
    void 山田次郎というEmployeeに対してEmployeeResponseが作成できる() {
        Employee employee = new Employee("2", "Jiro", "Yamada");
        EmployeeResponse actual =  EmployeeResponse.createResponse(employee);

        EmployeeResponse expected = new EmployeeResponse("2", "Jiro", "Yamada");

        assertEquals(expected, actual);
    }
}
