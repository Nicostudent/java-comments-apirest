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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.NoHandlerFoundException;

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
    public MultilingualComment getCommentById(@PathVariable String id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Can't find the comment: " + id));
    }
    
    @PostMapping("/comment")
    @io.swagger.v3.oas.annotations.Operation(hidden = false)
    public MultilingualComment postComment(@RequestBody MultilingualComment multilingualComment) {        
        return commentRepository.save(multilingualComment);
    }
    
    @PutMapping("/comment/{id}")
    @io.swagger.v3.oas.annotations.Operation(hidden = false)
    public MultilingualComment updateComment(@PathVariable String id, @RequestBody MultilingualComment updatedComment) {
        MultilingualComment existingComment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Can't find the comment: " + id));
        
        existingComment.setName(updatedComment.getName());
        existingComment.setSubject(updatedComment.getSubject());
        existingComment.setResponse(updatedComment.getResponse());
        existingComment.setPositiveComment(updatedComment.isPositiveComment());
        
        return commentRepository.save(existingComment);
    }
    
    @DeleteMapping("/comment/{id}")
    @io.swagger.v3.oas.annotations.Operation(hidden = false)
    public String deleteComment(@PathVariable String id) {
        MultilingualComment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Can't find the comment: " + id));
        commentRepository.delete(comment);
        return "The comment with ID: " + id + " Deleted successfully";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Route not found: " + ex.getRequestURL());
    }
}
