package org.ernmrkc.committracker.Modules.Commit;

import org.ernmrkc.committracker.Modules.Commit.Models.Commit;
import org.ernmrkc.committracker.Modules.Commit.QueryHandlers.GetAllCommitsQueryHandler;
import org.ernmrkc.committracker.Modules.Commit.QueryHandlers.GetCommitByIdQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/commits")
public class CommitController {
    private GetAllCommitsQueryHandler getAllCommitsQueryHandler;
    private GetCommitByIdQueryHandler getCommitByIdQueryHandler;

    public CommitController(GetAllCommitsQueryHandler getAllCommitsQueryHandler,
                            GetCommitByIdQueryHandler getCommitByIdQueryHandler) {
        this.getAllCommitsQueryHandler = getAllCommitsQueryHandler;
        this.getCommitByIdQueryHandler = getCommitByIdQueryHandler;
    }

    @GetMapping("/all")
    public String getAllCommits(Model model){
        List<Commit> commitList = getAllCommitsQueryHandler.execute(null).getBody();
        model.addAttribute("commitList", commitList);
        return "commits_list";
    }
    @GetMapping("/id:{id}")
    public String getCommitDetails(@PathVariable Long id, Model model) {
        Commit commit = getCommitByIdQueryHandler.execute(id).getBody();
        model.addAttribute("commit",commit);
        return "commit_details";
    }
}
