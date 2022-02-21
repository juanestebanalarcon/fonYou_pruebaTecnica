package com.fonyou.apifonYouApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.apifonYouApp.Entity.EstudianteEntity;

import antlr.collections.List;
public interface IEstudianteRepository extends JpaRepository<EstudianteEntity,Long>{
List findById(int id);
List findByNombre(String nombre);
}
