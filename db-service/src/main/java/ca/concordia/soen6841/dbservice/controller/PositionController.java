package ca.concordia.soen6841.dbservice.controller;

import ca.concordia.soen6841.dbservice.exceptions.PositionNotFoundException;
import ca.concordia.soen6841.dbservice.model.Position;
import ca.concordia.soen6841.dbservice.repository.PositionRepository;
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

}