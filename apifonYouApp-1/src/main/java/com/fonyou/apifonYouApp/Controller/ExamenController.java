package com.fonyou.apifonYouApp.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fonyou.apifonYouApp.Repository.IExamenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fonyou.apifonYouApp.Entity.ExamenEntity;
import com.fonyou.apifonYouApp.Entity.PreguntasEntity;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ExamenController {
@Autowired
IExamenRepository examenRepository;

@GetMapping("/examenes")
public ResponseEntity<List<ExamenEntity>>getAllExamenes(@RequestParam(required=false) int id)
{
try {
	List<ExamenEntity>examenes = new ArrayList<ExamenEntity>();
	if(id <=0) {
		examenRepository.findAll().forEach(examenes::add);
	}else if(examenes.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(examenes, HttpStatus.OK);
}catch(Exception e) {
	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@GetMapping("/examenes/{id}")
public ResponseEntity<ExamenEntity> getExamenById(@PathVariable("id") long id) {
	Optional<ExamenEntity> examenData = examenRepository.findById(id);

	if (examenData.isPresent()) {
		return new ResponseEntity<>(examenData.get(), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
@PostMapping("/examenes")
public ResponseEntity<ExamenEntity> createExamen(@RequestBody ExamenEntity examenes) {
	try {
		//public ExamenEntity(int puntaje, PreguntasEntity preguntas,int cantidadPreguntas)
		ExamenEntity _examenes = examenRepository.save(new ExamenEntity(examenes.getPuntaje(),examenes.getPreguntas()));
		return new ResponseEntity<>(_examenes, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@PutMapping("/examenes/{id}")
public ResponseEntity<ExamenEntity> updateExamen(@PathVariable("id") long id, @RequestBody ExamenEntity examenes) {
	Optional<ExamenEntity> examenData = examenRepository.findById(id);

	if (examenData.isPresent()) {
		ExamenEntity _examenes = examenData.get();
		_examenes.setPuntaje(examenes.getPuntaje());
		_examenes.setPreguntas(examenes.getPreguntas());
		return new ResponseEntity<>(examenRepository.save(_examenes), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

@DeleteMapping("/examenes/{id}")
public ResponseEntity<HttpStatus> deleteExamen(@PathVariable("id") long id) {
	try {
		examenRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@DeleteMapping("/examenes")
public ResponseEntity<HttpStatus> deleteAllExamenes() {
	try {
		examenRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
}