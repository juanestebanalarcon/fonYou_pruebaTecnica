package com.fonyou.apifonYouApp.Controller;

import java.security.Timestamp;
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
import com.fonyou.apifonYouApp.Repository.IEstudianteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fonyou.apifonYouApp.Entity.EstudianteEntity;
import com.fonyou.apifonYouApp.Entity.ExamenEntity;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EstudianteController {
@Autowired
IEstudianteRepository estudianteRepository;

@GetMapping("/estudiantes")
public ResponseEntity<List<EstudianteEntity>>getAllEstudiante(@RequestParam(required=false) int id)
{
try {
	List<EstudianteEntity>estudiantes = new ArrayList<EstudianteEntity>();
	if(id <=0) {
		estudianteRepository.findAll().forEach(estudiantes::add);
	}else if(estudiantes.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(estudiantes, HttpStatus.OK);
}catch(Exception e) {
	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@GetMapping("/estudiantes/{id}")
public ResponseEntity<EstudianteEntity> getEstidianteById(@PathVariable("id") long id) {
	Optional<EstudianteEntity> estudianteData = estudianteRepository.findById(id);

	if (estudianteData.isPresent()) {
		return new ResponseEntity<>(estudianteData.get(), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
@PostMapping("/estudiantes")
public ResponseEntity<EstudianteEntity> createEstudiante(@RequestBody EstudianteEntity estudiantes) {
	try {
		//String nombre, int edad, String ciudad, Timestamp zona_horaria,
		//ExamenEntity examen
		EstudianteEntity _estudiantes = estudianteRepository.save(new EstudianteEntity(estudiantes.getNombre(),estudiantes.getEdad(),estudiantes.getCiudad(),estudiantes.getZona_horaria(),estudiantes.getExamen()));
		return new ResponseEntity<>(_estudiantes, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@PutMapping("/estudiante/{id}")
public ResponseEntity<EstudianteEntity> updateEstudiante(@PathVariable("id") long id, @RequestBody EstudianteEntity estudiantes) {
	Optional<EstudianteEntity> estudianteData = estudianteRepository.findById(id);

	if (estudianteData.isPresent()) {
		EstudianteEntity _estudiantes = estudianteData.get();
		_estudiantes.setZona_horaria(estudiantes.getZona_horaria());
	//	_estudiantes.setExamen(estudiantes.getExamen().getPuntaje());
		return new ResponseEntity<>(estudianteRepository.save(_estudiantes), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

@DeleteMapping("/estudiantes/{id}")
public ResponseEntity<HttpStatus> deleteEstudiante(@PathVariable("id") long id) {
	try {
		estudianteRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@DeleteMapping("/estudiantes")
public ResponseEntity<HttpStatus> deleteAllEstudiantes() {
	try {
		estudianteRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
}