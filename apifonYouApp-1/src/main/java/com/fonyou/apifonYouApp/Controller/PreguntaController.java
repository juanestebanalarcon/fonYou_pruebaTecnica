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
import com.fonyou.apifonYouApp.Repository.IPreguntasRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.fonyou.apifonYouApp.Entity.PreguntasEntity;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PreguntaController {
@Autowired
IPreguntasRepository preguntasRepository;

@GetMapping("/preguntas")
public ResponseEntity<List<PreguntasEntity>>getAllPreguntas(@RequestParam(required=false) int id)
{
try {
	List<PreguntasEntity>preguntas = new ArrayList<PreguntasEntity>();
	if(id <=0) {
	preguntasRepository.findAll().forEach(preguntas::add);
	}else if(preguntas.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<>(preguntas, HttpStatus.OK);
}catch(Exception e) {
	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@GetMapping("/preguntas/{id}")
public ResponseEntity<PreguntasEntity> getPreguntaById(@PathVariable("id") long id) {
	Optional<PreguntasEntity> PreguntaData = preguntasRepository.findById(id);

	if (PreguntaData.isPresent()) {
		return new ResponseEntity<>(PreguntaData.get(), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
@PostMapping("/preguntas")
public ResponseEntity<PreguntasEntity> createPregunta(@RequestBody PreguntasEntity preguntas) {
	try {
		//public PreguntasEntity(String enunciado, boolean opc_a, boolean opc_b, boolean opc_c, boolean opc_d,int valor_pregunta)
		PreguntasEntity _preguntas = preguntasRepository.save(new PreguntasEntity(preguntas.getEnunciado(),preguntas.isOpc_a(),preguntas.isOpc_b(),preguntas.isOpc_c(),preguntas.isOpc_d(),preguntas.getValorPregunta()));
		return new ResponseEntity<>(_preguntas, HttpStatus.CREATED);
	} catch (Exception e) {
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@PutMapping("/preguntas/{id}")
public ResponseEntity<PreguntasEntity> updatePregunta(@PathVariable("id") long id, @RequestBody PreguntasEntity preguntas) {
	Optional<PreguntasEntity> preguntaData = preguntasRepository.findById(id);

	if (preguntaData.isPresent()) {
		PreguntasEntity _pregunta = preguntaData.get();
		_pregunta.setEnunciado(preguntas.getEnunciado());
		_pregunta.setValorPregunta(preguntas.getValorPregunta());
		_pregunta.setOpc_a(preguntas.isOpc_a());
		_pregunta.setOpc_b(preguntas.isOpc_b());
		_pregunta.setOpc_c(preguntas.isOpc_c());
		_pregunta.setOpc_d(preguntas.isOpc_d());
		return new ResponseEntity<>(preguntasRepository.save(_pregunta), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

@DeleteMapping("/preguntas/{id}")
public ResponseEntity<HttpStatus> deletePregunta(@PathVariable("id") long id) {
	try {
		preguntasRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@DeleteMapping("/preguntas")
public ResponseEntity<HttpStatus> deleteAllPreguntas() {
	try {
		preguntasRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
@SuppressWarnings("unchecked")
@GetMapping("/preguntas/correctas")
public ResponseEntity<List<PreguntasEntity>> findByCorrectas() {
	try {
		List<PreguntasEntity> preguntas = (List<PreguntasEntity>) preguntasRepository.findByCorrectas(true);

		if (preguntas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(preguntas, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

}