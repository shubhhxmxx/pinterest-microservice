package com.infy.repo;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.FollowActivity;

public interface FollowActivityRepo extends JpaRepository<FollowActivity,BigInteger> {
	Optional<FollowActivity> findByFollowerAndUserId(BigInteger follower, BigInteger userId);

	List<FollowActivity> findAllByUserId(BigInteger userId);

	List<FollowActivity> findAllByFollower(BigInteger follower);
}
