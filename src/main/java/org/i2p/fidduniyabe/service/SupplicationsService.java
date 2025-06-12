package org.i2p.fidduniyabe.service;
import org.i2p.fidduniyabe.model.SupplicationType;
import org.i2p.fidduniyabe.model.Supplications;
import org.i2p.fidduniyabe.repository.SupplicationTypeRepository;
import org.i2p.fidduniyabe.repository.SupplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplicationsService {

    @Autowired
    private SupplicationsRepository supplicationsRepository;
    @Autowired
    private SupplicationTypeRepository supplicationTypeRepository;


    @Autowired
    public SupplicationsService(SupplicationsRepository supplicationsRepository) {
        this.supplicationsRepository = supplicationsRepository;
    }



//
//    public Supplications add(Supplications supplication) {
//        Long typeId = supplication.getSupplicationTypeID().getSupplicationTypeId();
//
//        // Fetch the existing SupplicationType from the database
//        supplication.setSupplicationTypeID(
//                supplicationTypeRepository.findById(typeId)
//                        .orElseThrow(() -> new IllegalArgumentException("Invalid SupplicationType ID: " + typeId))
//        );
//
//        return supplicationsRepository.save(supplication);
//    }

//    public Supplications add(Supplications supplication) {
//        Long typeId = supplication.getSupplicationTypeID().getSupplicationTypeId();
//        SupplicationType existingType = supplicationTypeRepository.findById(typeId)
//                .orElseThrow(() -> new IllegalArgumentException("SupplicationType ID " + typeId + " not found."));
//        supplication.setSupplicationTypeID(existingType); // replace transient with managed instance
//
//        return supplicationsRepository.save(supplication);
//    }


    public Supplications add(Supplications supplication) {
        Supplications newsupplication  =  supplicationsRepository.save(supplication);
        return  newsupplication;
    }


    //update any fields of supplications
    public Supplications update(Long id, Supplications updatedSupplication) {
        return supplicationsRepository.findById(id).map(existing -> {

//            existing.setSupIdInCsv(updatedSupplication.getSupIdInCsv() > 0 ? updatedSupplication.getSupIdInCsv() : 0);

            if (updatedSupplication.getArabic() != null) {
                existing.setArabic(updatedSupplication.getArabic());
            }
            if (updatedSupplication.getEnglish() != null) {
                existing.setEnglish(updatedSupplication.getEnglish());
            }
            if (updatedSupplication.getRoman() != null) {
                existing.setRoman(updatedSupplication.getRoman());
            }

            if (updatedSupplication.getHeading() != null) {
                existing.setHeading(updatedSupplication.getHeading());
            }
            existing.setTotalCount(updatedSupplication.getTotalCount() > 0 ? updatedSupplication.getTotalCount() : 0);


            //setting number of sub categories
                existing.setNumSubCategories(updatedSupplication.getNumSubCategories() > 0 ? updatedSupplication.getNumSubCategories() : 0);


            if (updatedSupplication.getSubHeading() != null) {
                existing.setSubHeading(updatedSupplication.getSubHeading());
            }

            existing.setNumOfDuas(updatedSupplication.getNumOfDuas() > 0 ? updatedSupplication.getNumOfDuas() : 0);

            if (updatedSupplication.getRefType() != null) {
                existing.setRefType(updatedSupplication.getRefType());
            }
            if (updatedSupplication.getRefText() != null) {
                existing.setRefText(updatedSupplication.getRefText());
            }
            if (updatedSupplication.getRefChapter() != null) {
                existing.setRefChapter(updatedSupplication.getRefChapter());
            }

            if (updatedSupplication.getHeadingRoman() != null) {
                existing.setHeadingRoman(updatedSupplication.getHeadingRoman());
            }
            existing.setUpdatedAt(LocalDateTime.now());

            return supplicationsRepository.save(existing);
        }).orElse(null);
    }//update ended



    public boolean removeSupplication(Long id) {
        Optional<Supplications> optional = supplicationsRepository.findById(id);
        if (optional.isPresent()) {
            Supplications supplication = optional.get();
            supplication.setIsActive(false);
            Long typeId = supplication.getSupplicationType().getTypeId();

            SupplicationType type = supplicationTypeRepository.findById(typeId)
                    .orElseThrow(() -> new IllegalArgumentException("SupplicationType with ID " + typeId + " not found."));

            supplication.setSupplicationType(type);
            supplicationsRepository.save(supplication);
            return true;
        }
        return false;
    }

    public void deleteById(Long id) {
    supplicationsRepository.deleteById(id);
    }


    public List<Supplications> findAll() {
        return supplicationsRepository.findAllByIsActiveTrue();
    }

    public List<Supplications> findAllIncludingInActive() {
        return (List<Supplications>) supplicationsRepository.findAll();
    }


}
