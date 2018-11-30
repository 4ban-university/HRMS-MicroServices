package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.PositionNotFoundException;
import ca.concordia.soen6841.dbservice.model.Position;
import ca.concordia.soen6841.dbservice.repository.PositionRepository;
import ca.concordia.soen6841.dbservice.payloads.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/")
    public List<Position> getPosition() {
        return positionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Integer id) {
        return positionRepository.findById(id)
                .orElseThrow(() -> new PositionNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public String deletePosition(@PathVariable Integer id) {
        positionRepository.deleteById(id);
        return "Position deleted successfully";
    }

    @PostMapping("/")
    public CustomResponse<Position> createPosition(@RequestBody Position newPosition) {
        Position position = new Position();

        position.setDesignation(newPosition.getDesignation());
        position.setDepartmentName(newPosition.getDepartmentName());
        position.setStartDate(newPosition.getStartDate());
        position.setEndDate(newPosition.getEndDate());
        position.setEmployee(newPosition.getEmployee());

        CustomResponse<Position> response =  new CustomResponse<>();
        response.setData(positionRepository.save(position));
        response.setMessage("Position created successfully");
        return response;
    }
}