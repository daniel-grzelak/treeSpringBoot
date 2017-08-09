package com.daniel.dao;

import com.daniel.domain.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kapelusznik on 05.07.2017.
 */

@Repository
public interface NodeDao extends JpaRepository<Node, Long>{





}
