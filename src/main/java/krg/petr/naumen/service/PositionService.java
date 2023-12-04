package krg.petr.naumen.service;

import krg.petr.naumen.model.Position;

import java.util.List;

public interface PositionService {

    List<Position> getPositionsByDepartmentId(Long idDepartment);

    List<String> getPositionByDivisionName(String name);
}