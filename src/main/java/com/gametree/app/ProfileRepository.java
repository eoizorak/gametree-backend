package com.gametree.app;

import org.springframework.data.jpa.repository.JpaRepository;

// .save(), .findAll(), .delete()
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}