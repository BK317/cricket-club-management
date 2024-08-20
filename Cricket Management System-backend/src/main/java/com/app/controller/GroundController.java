package com.app.controller;


	import com.app.dto.GroundDTO;
	import com.app.service.GroundService;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import java.util.List;

	@RestController
	@RequestMapping("/grounds")
	public class GroundController {

	    @Autowired
	    private GroundService groundService;

	    @PostMapping
	    public ResponseEntity<GroundDTO> addGround(@RequestBody GroundDTO groundDTO) {
	        GroundDTO savedGround = groundService.addGround(groundDTO);
	        return ResponseEntity.ok(savedGround);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<GroundDTO> getGroundById(@PathVariable Long id) {
	        GroundDTO ground = groundService.getGroundById(id);
	        return ResponseEntity.ok(ground);
	    }

	    @GetMapping
	    public ResponseEntity<List<GroundDTO>> getAllGrounds() {
	        List<GroundDTO> grounds = groundService.getAllGrounds();
	        return ResponseEntity.ok(grounds);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<GroundDTO> updateGround(@PathVariable Long id, @RequestBody GroundDTO groundDTO) {
	        GroundDTO updatedGround = groundService.updateGround(id, groundDTO);
	        return ResponseEntity.ok(updatedGround);
	    }
	
	
}
