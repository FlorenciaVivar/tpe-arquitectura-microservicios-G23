package tpe.microserviciostation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tpe.microserviciostation.entities.StationEntity;

public interface StationRepository extends MongoRepository<StationEntity,String> {


}
