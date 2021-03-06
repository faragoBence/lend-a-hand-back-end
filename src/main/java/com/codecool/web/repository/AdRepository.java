package com.codecool.web.repository;

import com.codecool.web.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByStateOrderByIsPremiumDescTimestampDesc(String state);

    List<Ad> findAllByStateAndAdvertiser_IdOrderByIsPremiumDescTimestampDesc(String state, int id);

    List<Ad> findAllByOrderByIsPremiumDescTimestampDesc();

    Ad findById(int id);

    List<Ad> findAllByStateAndCategoryOrderByIsPremiumDescTimestampDesc(String state, String category);

    List<Ad> findAllByStateAndTitleContainingIgnoreCaseOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword);

    List<Ad> findAllByStateAndDescriptionContainingIgnoreCaseOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword);

    List<Ad> findAllByStateAndTypeOrderByIsPremiumDescTimestampDesc(String state, @Nullable String type);

    List<Ad> findAllByStateAndCategoryAndTypeOrderByIsPremiumDescTimestampDesc(String state, @Nullable String category, @Nullable String type);

    //Find ads by keyword and category
    List<Ad> findAllByStateAndTitleContainingIgnoreCaseAndCategoryOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword, @Nullable String category);

    List<Ad> findAllByStateAndDescriptionContainingIgnoreCaseAndCategoryOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword, @Nullable String category);

    //Find ads by keyword and type
    List<Ad> findAllByStateAndTitleContainingIgnoreCaseAndTypeOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword, @Nullable String type);

    List<Ad> findAllByStateAndDescriptionContainingIgnoreCaseAndTypeOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword, @Nullable String type);

    //Find ads by keyword, category and type
    List<Ad> findAllByStateAndTitleContainingIgnoreCaseAndCategoryAndTypeOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword, @Nullable String category, @Nullable String type);

    List<Ad> findAllByStateAndDescriptionContainingIgnoreCaseAndCategoryAndTypeOrderByIsPremiumDescTimestampDesc(String state, @Nullable String keyword, @Nullable String category, @Nullable String type);


}
