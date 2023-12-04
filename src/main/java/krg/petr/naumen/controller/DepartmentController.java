package krg.petr.naumen.controller;

import krg.petr.naumen.model.Department;
import krg.petr.naumen.service.DepartmentService;
import krg.petr.naumen.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/organizational-structure")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @ResponseBody
    @GetMapping("/get-departments")
    public ResponseEntity<List<Department>> getDepartments() {
        List<Department> departments = departmentService.getDepartmentsByParent(0L);
        return ResponseEntity.ok(departments);
    }

    @ResponseBody
    @GetMapping("/get-division/{departmentName}")
    public ResponseEntity<List<String>> getDepartments(@PathVariable String departmentName) {
        List<String> departments = departmentService.getDivisionByDepartmentName(departmentName);
        return ResponseEntity.ok(departments);
    }

    @ResponseBody
    @GetMapping("/get-positions/{divisionName}")
    public ResponseEntity<List<String>> getPositions(@PathVariable String divisionName) {
        List<String> positions = positionService.getPositionByDivisionName(divisionName);
        return ResponseEntity.ok(positions);
    }
}