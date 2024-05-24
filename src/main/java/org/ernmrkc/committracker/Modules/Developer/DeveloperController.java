package org.ernmrkc.committracker.Modules.Developer;

import jakarta.validation.Valid;
import org.ernmrkc.committracker.Modules.Developer.CommandHandlers.CreateDeveloperCommandHandler;
import org.ernmrkc.committracker.Modules.Developer.CommandHandlers.DeleteDeveloperCommandHandler;
import org.ernmrkc.committracker.Modules.Developer.Models.Developer;
import org.ernmrkc.committracker.Modules.Developer.QueryHandlers.GetAllDevelopersQueryHandlers;
import org.ernmrkc.committracker.Modules.Developer.QueryHandlers.GetDeveloperByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/developers")
public class DeveloperController {
    private final GetAllDevelopersQueryHandlers getAllDevelopersQueryHandlers;
    private final GetDeveloperByIdQueryHandler getDeveloperByIdQueryHandler;
    private final CreateDeveloperCommandHandler createDeveloperCommandHandler;
    private final DeleteDeveloperCommandHandler deleteDeveloperCommandHandler;

    public DeveloperController(GetAllDevelopersQueryHandlers getAllDevelopersQueryHandlers,
                               GetDeveloperByIdQueryHandler getDeveloperByIdQueryHandler,
                               CreateDeveloperCommandHandler createDeveloperCommandHandler,
                               DeleteDeveloperCommandHandler deleteDeveloperCommandHandler) {
        this.getAllDevelopersQueryHandlers = getAllDevelopersQueryHandlers;
        this.getDeveloperByIdQueryHandler = getDeveloperByIdQueryHandler;
        this.createDeveloperCommandHandler = createDeveloperCommandHandler;
        this.deleteDeveloperCommandHandler = deleteDeveloperCommandHandler;
    }

    @GetMapping("/getAllDevelopers")
    public ResponseEntity<List<Developer>> getAllDevelopers(){
        return getAllDevelopersQueryHandlers.execute(null);
    }

    @GetMapping("/getById")
    public ResponseEntity<Developer> getDeveloperById(@RequestParam(value = "id") Long id){
        return getDeveloperByIdQueryHandler.execute(id);
    }

    @PostMapping("/createDeveloper")
    public ResponseEntity<Developer> createDeveloper(@Valid @RequestBody Developer developer, BindingResult bindingResult){
        return createDeveloperCommandHandler.execute(developer, bindingResult);
    }

    @DeleteMapping("/deleteDeveloper")
    public ResponseEntity<Void> deleteDeveloper(@RequestParam(value = "id") Long id){
        return deleteDeveloperCommandHandler.execute(id, null);
    }

    @GetMapping("/id:{id}")
    public String showDeveloperDetails(@PathVariable Long id, Model model) {
        Developer developer = getDeveloperByIdQueryHandler.execute(id).getBody();
        model.addAttribute("developer", developer);
        return "developer_details";
    }

}
