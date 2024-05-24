package com.nicorojo.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicorojo.apirest.apirest.Entities.MultilingualComment;
import com.nicorojo.apirest.apirest.Repositories.MultilingualCommentRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1")
public class CommentsController {

    @Autowired
    private MultilingualCommentRepository commentRepository;

    @GetMapping("/comments")
    public List<MultilingualComment> getAllComments() {
        return commentRepository.findAll();
    }
    
    @GetMapping("/comment/{id}")
    public MultilingualComment getCommnetById(@PathVariable String id) {
        return commentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Can't find the comment: " + id));
    }
    
    @PostMapping("/comment")
    @io.swagger.v3.oas.annotations.Operation(hidden = false)
    public MultilingualComment postMethodName(@RequestBody MultilingualComment multilingualComent) {        
        return commentRepository.save(multilingualComent);
    }

    @PutMapping("/comment/{id}")
    @io.swagger.v3.oas.annotations.Operation(hidden = false)
    public MultilingualComment updateComment(@PathVariable String id, @RequestBody MultilingualComment updatedComment) {
        MultilingualComment comment = commentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Can't find the comment: " + id));

        comment.setName(updatedComment.getName());
        comment.setSubject(updatedComment.getSubject());
        comment.setResponse(updatedComment.getResponse());
        comment.setPositiveComment(updatedComment.isPositiveComment());

        return commentRepository.save(comment);
    }
    @DeleteMapping("/comment/{id}")
    @io.swagger.v3.oas.annotations.Operation(hidden = false)
    public String delleteComment(@PathVariable String id){
        MultilingualComment comment = commentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Can't find the comment: " + id));
        commentRepository.delete(comment);
        return "The comment with ID: " + id + " Deleted succefully";
    }
}
