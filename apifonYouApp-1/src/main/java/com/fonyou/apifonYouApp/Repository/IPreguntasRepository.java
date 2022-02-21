package com.fonyou.apifonYouApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.apifonYouApp.Entity.PreguntasEntity;

import antlr.collections.List;
public interface IPreguntasRepository extends JpaRepository<PreguntasEntity,Long>{
List findById(int id);
List findByCorrectas(boolean correctas);
}
