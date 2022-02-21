package com.fonyou.apifonYouApp.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.apifonYouApp.Entity.ExamenEntity;

import antlr.collections.List;
public interface IExamenRepository extends JpaRepository<ExamenEntity,Long>{
List findById(int id);
}
